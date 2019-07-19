package bowling.model.frame;

import bowling.model.frame.state.Score;

import static bowling.model.frame.state.Score.DEFAULT_SCORE;

public class FrameResult {

    private String state;
    private int score;

    FrameResult(int score, String state) {
        this.score = score;
        this.state = state;
    }

    static FrameResult of(Score score, State state) {
        if (score.isCompleted() && state.isFinished()) {
            return new FrameResult(score.getScore(), state.printResult());
        }
        return new FrameResult(DEFAULT_SCORE, state.printResult());
    }

    public String getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public void calculate(FrameResult prevFrameResult) {
        if (DEFAULT_SCORE < this.score) {
            this.score += prevFrameResult.getScore();
        }
    }
}