package bowling.domain.frame;

import bowling.domain.bonusscore.BonusScores;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 마지막 프레임을 나타내기 위한 객체
 * 마지막 프레임에서 스트라이크, 스페어 일 경우 1구를 더 진행한다.
 */
public class LastFrame implements Frame {
    private static final int STRIKE_POINT = 10;
    private static final int DEFAULT_PLAY_COUNT = 2;
    private static final int BONUS_PLAY_COUNT = 3;
    private static final int FIRST_PLAY = 0;
    private static final int SECOND_PLAY = 1;
    private static final int DOUBLE_STRIKE_POINT = 20;
    private static final int ZERO_POINT = 0;
    private static final int THIRD_PLAY = 2;

    private final Scores scores;
    private final BonusScores bonusScores;

    public LastFrame(BonusScores bonusScores) {
        this.scores = new Scores();
        this.bonusScores = bonusScores;
    }

    @Override
    public void addScore(int point) {
        if (scores.isSecondPlay()) {
            validateSecondPoint(point);
        }

        if (scores.isThirdPlay()) {
            validateThirdPoint(point);
        }

        scores.add(new Score(generateLastScoreType(scores, point), point));
        bonusScores.addBonusPoint(point);
    }

    private ScoreType generateLastScoreType(Scores scores, int point) {
        if (point == STRIKE_POINT && scores.size() * STRIKE_POINT == scores.currentPoint()) {
            return ScoreType.STRIKE;
        }

        if (scores.size() == THIRD_PLAY) {
            return getThirdPlayScoreType(scores, point);
        }
        return getSpareOrGutterType(scores, point);
    }

    private ScoreType getThirdPlayScoreType(Scores scores, int point) {
        if (scores.isSpare(SECOND_PLAY) && point == STRIKE_POINT) {
            return ScoreType.STRIKE;
        }
        if (!scores.isSpare(SECOND_PLAY) && scores.currentPoint() + point == DOUBLE_STRIKE_POINT) {
            return ScoreType.SPARE;
        }
        return getSpareOrGutterType(scores, point);
    }

    private ScoreType getSpareOrGutterType(Scores scores, int point) {
        if (point == ZERO_POINT) {
            return ScoreType.GUTTER;
        }
        if (scores.size() == SECOND_PLAY && scores.currentPoint() + point == STRIKE_POINT) {
            return ScoreType.SPARE;
        }
        return ScoreType.MISS;
    }

    private void validateSecondPoint(int point) {
        if (!scores.isStrike(FIRST_PLAY) && totalPoint(point) > STRIKE_POINT) {
            throw new IllegalArgumentException("마지막 포인트합은 10점을 넘을수 없습니다.");
        }
    }

    private void validateThirdPoint(int point) {
        if (!scores.isStrike(SECOND_PLAY) && totalPoint(point) > DOUBLE_STRIKE_POINT) {
            throw new IllegalArgumentException("마지막 포인트합은 20점을 넘을수 없습니다.");
        }
    }

    private int totalPoint(int point) {
        return scores.currentPoint() + point;
    }

    @Override
    public boolean isPlayable() {
        if (CollectionUtils.isEmpty(scores.getScores())) {
            return true;
        }
        if (scores.size() < DEFAULT_PLAY_COUNT) {
            return true;
        }

        return scores.size() < BONUS_PLAY_COUNT && scores.hasStrikeOrSpare();
    }

    @Override
    public List<Score> getScores() {
        return new ArrayList<>(scores.getScores());
    }

    @Override
    public int getTotalPoint(int frameIndex) {
        return scores.currentPoint();
    }

    @Override
    public boolean isCalculatableFrame(int frameIndex) {
        return !isPlayable();
    }
}
