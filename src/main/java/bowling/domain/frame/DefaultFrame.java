package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScore;
import bowling.domain.bonusscore.BonusScores;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 프레임은 점수를 기록하고 다음 프레임을 생성하는 책임을 가진다.
 */
public class DefaultFrame implements Frame {
    private static final int STRIKE_POINT = 10;
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int FIRST_PLAY = 0;

    private final Scores scores;
    private final BonusScores bonusScores;

    private DefaultFrame(BonusScores bonusScores) {
        this.scores = new Scores();
        this.bonusScores = bonusScores;
    }

    public static DefaultFrame first() {
        return new DefaultFrame(new BonusScores());
    }

    public DefaultFrame createNextFrame(int frameIndex) {
        addBounusScoresHasStrikeOrSpare(frameIndex);
        return new DefaultFrame(bonusScores.findAddableBonusScores());
    }

    public LastFrame createLastFrame(int frameIndex) {
        addBounusScoresHasStrikeOrSpare(frameIndex);
        return new LastFrame(bonusScores.findAddableBonusScores());
    }

    private void addBounusScoresHasStrikeOrSpare(int frameIndex) {
        if (scores.hasStrikeOrSpare()) {
            bonusScores.add(createBonusScore(frameIndex));
        }
    }

    private BonusScore createBonusScore(int frameIndex) {
        if (scores.isStrike(FIRST_PLAY)) {
            return BonusScore.strikeBonus(frameIndex);
        }
        return BonusScore.spareBonus(frameIndex);
    }

    @Override
    public void addScore(int point) {
        if (scores.currentPoint() + point > STRIKE_POINT) {
            throw new IllegalArgumentException("한 프레임의 포인트는 10점을 넘을수 없습니다.");
        }
        scores.add(Score.defaultFrameScore(scores, point));
        bonusScores.addBonusPoint(point);
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return true;
        }
        if (scores.isStrike(FIRST_PLAY)) {
            return false;
        }
        return scores.size() < DEFAULT_PLAY_COUNT;
    }

    @Override
    public List<Score> getScores() {
        return new ArrayList<>(scores.getScores());
    }

    @Override
    public int getTotalPoint(int frameIndex) {
        BonusScore bonusScore = bonusScores.findBonusScore(frameIndex);
        int totalPoint = scores.currentPoint();
        if (!Objects.isNull(bonusScore)) {
            return bonusScore.getTotalBonusPoint() + totalPoint;
        }

        return totalPoint;
    }

    @Override
    public boolean isCalculatableFrame(int frameIndex) {
        if (isPlayable()) {
            return false;
        }
        if (scores.hasStrikeOrSpare() && !bonusScores.isExistBonusScore(frameIndex)) {
            return false;
        }
        if (bonusScores.findBonusScore(frameIndex).isAddable()) {
            return false;
        }
        return true;
    }
}
