package bowling.domain.frame;

import bowling.domain.bonus.BonusScore;
import bowling.domain.bonus.BonusScores;
import bowling.domain.point.Point;
import bowling.domain.score.Scores;

import java.util.Objects;
import java.util.Optional;

public class FinalFrame extends Frame {
    private final static int BONUS_MAX_SCORE = 20;
    private final static int DEFAULT_MAX_SCORE = 10;

    private Point additionalPoint;

    public FinalFrame(BonusScores bonusScores, int frameIndex) {
        super(new Scores(), bonusScores, frameIndex);
    }

    @Override
    public void addPoint(int point) {
        validateScores(point);
        if ((scores.isStrike() || scores.isSpare()) && Objects.isNull(additionalPoint)) {
            additionalPoint = new Point(point);
            bonusScores.addBonusScore(BonusScore.lastBonusScore());
        }

        if (scores.isAvailableAdd()) {
            scores.addScore(point);
        }

        bonusScores.addBonusPoint(point);
    }

    @Override
    public void validateScores(int point) {
        if ((scores.isStrike() || scores.isSpare()) && scores.totalScore() + point > BONUS_MAX_SCORE) {
            throw new IllegalArgumentException("third frame score less than 20");
        }

        if (!(scores.isStrike() || scores.isSpare()) && scores.totalScore() + point > DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException("score less than 10");
        }
    }

    @Override
    public int totalScore() {
        return Optional.ofNullable(additionalPoint)
                .orElse(Point.MIN()).getPoint() + super.totalScore();
    }

    @Override
    public boolean isAvailablePlay() {
        if ((scores.isStrike() || scores.isSpare())) {
            return bonusScores.isAvailableAdd();
        }

        return scores.isAvailableAdd();
    }

    @Override
    public boolean isAvailableCalculatePoint() {
        return !isAvailablePlay();
    }
}
