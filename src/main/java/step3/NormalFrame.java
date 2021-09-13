package step3;

import step3.state.Ready;
import step3.state.State;

public class NormalFrame implements Frame {
    private Frame next;
    private State state;
    private int no;

    public NormalFrame(int frameNum) {
        this.state = new Ready();
        this.no = frameNum;
    }

    public Frame bowl(int fallenPins) {
        state = state.bowl(fallenPins);
        if (state.isFinish()) {
            next = createFrame();
            return next;
        }
        return this;
    }

    private Frame createFrame() {
        if (no + 1 == 10) {
            return new FinalFrame();
        }
        return new NormalFrame(no + 1);
    }

    public Score getScore() {
        Score score = state.score();
        if (score.canCalculateScore()) {
            return score;
        }

        return next.calculateAdditionalScore(score);
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    public boolean isGameEnd() {
        return false;
    }

    public State getState() {
        return state;
    }

    public boolean isFinish() {
        return state.isFinish();
    }
}
