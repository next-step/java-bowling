package bowling.domain.frame;

import bowling.domain.rolling.State;

import java.util.Objects;

public class Score {
    private static final int SCORE_ADDABLE_COUNT_DEFAULT = 0;
    private static final int SCORE_ADDABLE_COUNT_STRIKE = 2;
    private static final int SCORE_ADDABLE_COUNT_SPARE = 1;
    private static final int SCORE_ADDABLE_COUNT_DIFFERENCE = 1;

    private int score;
    private int scoreAddableCount;

    private Score(int score, int scoreAddableCount) {
        this.score = score;
        this.scoreAddableCount = scoreAddableCount;
    }

    static Score calculateStrike(int score) {
        return new Score(score, SCORE_ADDABLE_COUNT_STRIKE);
    }

    static Score calculateSpare(int score) {
        return new Score(score, SCORE_ADDABLE_COUNT_SPARE);
    }

    static Score calculateNormal(int score) {
        return new Score(score, SCORE_ADDABLE_COUNT_DEFAULT);
    }

    public static Score newScore(int score, int scoreAddableCount) {
        return new Score(score, scoreAddableCount);
    }

    public static Score calculateScore(Score lastScore, State state, int knockedDownPinCount) {
        int addableCount = getAddableCount(state);
        int score = knockedDownPinCount;

        if (!Objects.isNull(lastScore)) {
            score += lastScore.score;
        }

        return new Score(score, addableCount);
    }

    private static int getAddableCount(State state) {
        if (State.STRIKE.equals(state)) {
            return SCORE_ADDABLE_COUNT_STRIKE;
        }

        if (State.SPARE.equals(state)) {
            return SCORE_ADDABLE_COUNT_SPARE;
        }

        return SCORE_ADDABLE_COUNT_DEFAULT;
    }

    public Score calculate(int knockedDownPinCount) {
        return new Score(this.score + knockedDownPinCount,
                this.scoreAddableCount - SCORE_ADDABLE_COUNT_DIFFERENCE);
    }

    public void calculateAdditional(int knockedDownPinCount) {
        this.score += knockedDownPinCount;
        this.scoreAddableCount -= SCORE_ADDABLE_COUNT_DIFFERENCE;
    }

    public boolean isCalculateDone() {
        return scoreAddableCount == SCORE_ADDABLE_COUNT_DEFAULT;
    }

    public int getScore() {
        return score;
    }
}

