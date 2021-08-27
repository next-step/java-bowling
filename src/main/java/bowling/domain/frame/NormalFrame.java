package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;

public class NormalFrame implements Frame {

    private static final int NORMAL_FRAME_LAST = 9;

    private final int frameNumber;
    private Frame next;
    private State state;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        state = Ready.of();
    }

    public static NormalFrame of(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Frame bowl(Pins pins) {
        state = state.bowl(pins);
        if (state.isFinish()) {
            next = createNextFrame();
            return next;
        }
        return this;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public List<State> getState() {
        return Collections.singletonList(state);
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.canCalculateScore()) {
            return score;
        }

        return next.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        score = state.calculateAdditionalScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    private Frame createNextFrame() {
        if (frameNumber < NORMAL_FRAME_LAST) {
            return NormalFrame.of(frameNumber + 1);
        }
        return FinalFrame.of();
    }
}
