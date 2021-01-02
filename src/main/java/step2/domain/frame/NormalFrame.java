package step2.domain.frame;

import step2.domain.Score;
import step2.domain.state.State;
import step2.domain.state.StateFactory;

public class NormalFrame implements Frame {

    private int round;
    private State state;
    private Frame nextFrame;

    public NormalFrame(int round) {
        this.round = round;
        this.state = StateFactory.ready();
    }

    public static Frame init() {
        return new NormalFrame(DEFAULT);
    }

    @Override
    public Frame bowl(int fallingPins) {
        state = state.bowl(fallingPins);
        if (state.isFinish()) {
            nextFrame = createFrame(round);
            return nextFrame;
        }
        return this;
    }

    private Frame createFrame(int round) {
        if (round == FINAL_FRAME_NO - 1) {
            return new FinalFrame();
        }

        int nextIndex = ++round;
        return new NormalFrame(nextIndex);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }

        return nextFrame.calculateAdditionalScore(score);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public String toString() {
        return "" + state;
    }
}
