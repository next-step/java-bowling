package bowling.domain.frame;

import bowling.domain.rolling.State;

public class Score {
    private static final int DEFAULT_SCORE_ADDABLE_COUNT = 0;

    private int score = -1;
    private int scoreAddableCount;

    public Score() {

    }

    public Score(State state, int knockedDownPinCount) {
        this.scoreAddableCount = DEFAULT_SCORE_ADDABLE_COUNT;

        if (state == State.STRIKE) {
            this.scoreAddableCount = 2;
        }

        if (state == State.SPARE) {
            this.scoreAddableCount = 1;
        }

        this.score = knockedDownPinCount;
    }

    public static Score calculateScore(Score score, State state, int knockedDownPinCount) {

        return new Score();
    }

    public boolean isCalculateEnd() {
        return scoreAddableCount == DEFAULT_SCORE_ADDABLE_COUNT;
    }
}

