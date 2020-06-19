package bowling.domain.frame;

import bowling.domain.score.Scores;

public class FinalFrame extends Frame {
    private final static int BONUS_MAX_SCORE = 20;
    private final static int DEFAULT_MAX_SCORE = 10;

    public FinalFrame() {
        super(new Scores());
    }

    @Override
    public void validateScores() {
        if (bonusGame() && scores.totalScore() > BONUS_MAX_SCORE) {
            throw new IllegalArgumentException("third frame score less than 20");
        }

        if (!bonusGame() && scores.totalScore() > DEFAULT_MAX_SCORE) {
            throw new IllegalArgumentException("second frame score less than 10");
        }

    }

    @Override
    public boolean availablePlay() {
        if (scores.isStrike()) {
            return scores.firstScore();
        }

        if (scores.isSpare()) {
            return scores.secondScore();
        }

        return scores.firstScore();
    }

    private boolean bonusGame() {
        return scores.isStrike() || scores.isSpare();
    }
}
