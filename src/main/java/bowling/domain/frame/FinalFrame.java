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

    public FinalFrame(BonusScores bonusScores) {
        super(new Scores(), bonusScores);
    }

    @Override
    public void addPoint(int point) {
        validateScores(point);
        if (scores.isStrikeOrSpare() && Objects.isNull(additionalPoint)) {
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
        if (scores.isStrikeOrSpare() && scores.totalScore() + point > BONUS_MAX_SCORE) {
            throw new IllegalArgumentException("third frame score less than 20");
        }

        if (!scores.isStrikeOrSpare() && scores.totalScore() + point > DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException("score less than 10");
        }
    }

    @Override
    public int totalScore(int frameIndex) {
        return Optional.ofNullable(additionalPoint)
                .orElse(new Point(0)).getPoint() + super.totalScore(frameIndex);
    }

    @Override
    public boolean availablePlay() {
        if (isBonusGame()) {
            return bonusScores.isAvailableAdd();
        }

        return scores.isAvailableAdd();
    }

    private boolean isBonusGame() {
        return scores.isStrike() || scores.isSpare();
    }

    @Override
    public boolean isAvailableCalculatePoint(int frameIndex) {
        return !availablePlay();
    }
}
