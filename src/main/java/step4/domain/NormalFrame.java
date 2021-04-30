package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.State;

import java.util.Collections;
import java.util.List;

public class NormalFrame implements Frame {
    private static final int NORMAL_FRAME_INDEX_MAX = 8;

    private final int index;
    private State state;

    public NormalFrame(int index) {
        this(index, new Ready());
    }

    public NormalFrame(int index, State state) {
        this.index = index;
        this.state = state;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        state = state.bowl(Integer.parseInt(pinCount));

        if (isFinished()) {
            return next();
        }

        return this;
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public List<String> states() {
        return Collections.singletonList(state.marks());
    }

    @Override
    public Frame next() {
        if (index < NORMAL_FRAME_INDEX_MAX) {
            return new NormalFrame(index + 1);
        }

        return new FinalFrame();
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public Score score() {
        return state.score();
    }

    @Override
    public Score add(Score previousScore) {
        if (state instanceof Ready) {
            return Score.unCountableScore();
        }

        return state.addScore(previousScore);
    }
}
