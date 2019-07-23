package domain.frame;

import domain.Score;
import domain.state.State;

public class FrameResult {
    public static final int UNFINISHED_SCORE = -1;

    private String state;
    private int score;

    private FrameResult(String state, int score) {
        this.state = state;
        this.score = score;
    }

    public static FrameResult of(State state, Score score) {
        if (state.isClosed() && score.isFullyCalculated()) {
            return new FrameResult(state.printState(), score.getScore());
        }
        return new FrameResult(state.printState(), UNFINISHED_SCORE);
    }

    FrameResult updateResult(FrameResult previousResult) {
        if (score > UNFINISHED_SCORE) {
            score += previousResult.getScore();
        }
        return this;
    }

    public String getState() {
        return state;
    }

    public int getScore() {
        return score;
    }
}
