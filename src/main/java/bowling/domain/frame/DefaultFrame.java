package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScore;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 프레임은 점수를 기록하고 다음 프레임을 생성하는 책임을 가진다.
 */
public class DefaultFrame implements Frame {
    private static final int STRIKE_POINT = 10;
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int FIRST_PLAY = 0;

    private final Scores scores;
    private final List<BonusScore> bonusScores;

    private DefaultFrame(List<BonusScore> bonusScores) {
        this.scores = new Scores();
        this.bonusScores = bonusScores;
    }

    public static DefaultFrame first() {
        return new DefaultFrame(new ArrayList<>());
    }

    public DefaultFrame nextFrame(int frameIndex) {
        checkHasStrikeOrSpare(frameIndex);
        return new DefaultFrame(validateBonusScores());
    }

    public LastFrame lastFrame(int frameIndex) {
        checkHasStrikeOrSpare(frameIndex);
        return new LastFrame(validateBonusScores());
    }

    private void checkHasStrikeOrSpare(int frameIndex) {
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

    private List<BonusScore> validateBonusScores() {
        return bonusScores.stream()
                .filter(BonusScore::isAddable)
                .collect(Collectors.toList());
    }

    @Override
    public void addScore(int point) {
        if (scores.currentPoint() + point > STRIKE_POINT) {
            throw new IllegalArgumentException("한 프레임의 포인트는 10점을 넘을수 없습니다.");
        }
        scores.add(Score.defaultScore(scores, point));
        addBonusScore(point);
    }

    private void addBonusScore(int score) {
        bonusScores.stream()
                .filter(BonusScore::isAddable)
                .forEach(bonusScore -> bonusScore.add(score));
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
        return scores.getScores();
    }

    @Override
    public int getTotalPoint(int frameIndex) {
        BonusScore bonusScore = findBonusScore(frameIndex);
        int totalPoint = scores.currentPoint();
        if (!Objects.isNull(bonusScore)) {
            return bonusScore.totalBonusPoint() + totalPoint;
        }

        return totalPoint;
    }

    @Override
    public boolean isCalculatableFrame(int frameIndex) {
        if (isPlayable()) {
            return false;
        }
        if (scores.hasStrikeOrSpare() && !isExistBonusScore(frameIndex)) {
            return false;
        }
        if (findBonusScore(frameIndex).isAddable()) {
            return false;
        }
        return true;
    }

    private boolean isExistBonusScore(int frameIndex) {
        return bonusScores.stream()
                .anyMatch(bonusScore -> bonusScore.isEqualFrameIndex(frameIndex));
    }

    private BonusScore findBonusScore(int frameIndex) {
        return bonusScores.stream()
                .filter(bonusScore -> bonusScore.isEqualFrameIndex(frameIndex))
                .findFirst()
                .orElse(BonusScore.noneBonus(frameIndex));
    }
}
