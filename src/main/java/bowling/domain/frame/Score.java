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

    private Score(int scoreAddableCount, int score) {
        this.scoreAddableCount = scoreAddableCount;
        this.score = score;
    }

    public static Score calculateScore(Score lastScore, State state, int knockedDownPinCount) {
        int addableCount = getAddableCount(lastScore, state);
        int score = knockedDownPinCount;

        if (!Objects.isNull(lastScore)) {
            score += lastScore.score;
        }

        return new Score(addableCount, score);
    }

    private static int getAddableCount(Score score, State state) {
        if (!Objects.isNull(score) && score.scoreAddableCount != SCORE_ADDABLE_COUNT_DEFAULT) {
            score.scoreAddableCount -= SCORE_ADDABLE_COUNT_DIFFERENCE;
        }

        if (State.STRIKE.equals(state)) {
            return SCORE_ADDABLE_COUNT_STRIKE;
        }

        if (State.SPARE.equals(state)) {
            return SCORE_ADDABLE_COUNT_SPARE;
        }

        return SCORE_ADDABLE_COUNT_DEFAULT;
    }

    public boolean isCalculateEnd() {
        return scoreAddableCount == SCORE_ADDABLE_COUNT_DEFAULT;
    }
}

