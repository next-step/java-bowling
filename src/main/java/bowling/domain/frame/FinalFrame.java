package bowling.domain.frame;

import bowling.domain.bonus.BonusScores;
import bowling.domain.score.Scores;

public class FinalFrame extends Frame {
    private final static int BONUS_MAX_SCORE = 20;
    private final static int DEFAULT_MAX_SCORE = 10;

    public FinalFrame(BonusScores bonusScores) {
        super(new Scores(), bonusScores);
    }

    @Override
    public void addPoint(int point) {
        validateScores(point);
        if (!scores.isStrikeOrSpare()) {
            scores.addScore(point);
        }
        bonusScores.addBonusPoint(point);
    }

    @Override
    public void validateScores(int point) {
        if (scores.isStrikeOrSpare() && totalScore() + point > BONUS_MAX_SCORE) {
            throw new IllegalArgumentException("third frame score less than 20");
        }

        if (!scores.isStrikeOrSpare() && scores.totalScore() + point > DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException("score less than 10");
        }
    }

    @Override
    public boolean availablePlay() {
        if (scores.isEmpty()) {
            return true;
        }

        if (scores.isAvailableAdd()) {
            return true;
        }

        return isBonusGame() && bonusScores.isAvailableAdd();
    }

    private boolean isBonusGame() {
        return scores.isStrike() || scores.isSpare();
    }


}
