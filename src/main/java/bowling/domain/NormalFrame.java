package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private State state;

    private NormalFrame(FrameNumber frameNumber, State state) {
        this.frameNumber = frameNumber;
        this.state = state;
    }

    private static NormalFrame create(FrameNumber frameNumber, State state) {
        return new NormalFrame(frameNumber, state);
    }

    public static NormalFrame create(FrameNumber frameNumber) {
        return create(frameNumber, Ready.create());
    }

    public static NormalFrame initialize() {
        return create(FrameNumber.first());
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public int getFrameNumber() {
        return frameNumber.getValue();
    }

    @Override
    public String symbol() {
        return state.symbol();
    }

    @Override
    public Frame bowl(Pitching pitching) {
        this.state = state.bowl(pitching);

        if (state.isEnd()) {
            return createNextFrame();
        }

        return this;
    }

    private Frame createNextFrame() {
        if (frameNumber.next().isMax()) {
            return new LastFrame();
        }

        return create(frameNumber.next());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
