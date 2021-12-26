package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameIndex index;
    private Pin first;
    private Pin second;
    private State state;

    private NormalFrame(FrameIndex index, Pin pin, State state) {
        this.index = index;
        this.first = pin;
        this.state = state;
    }

    public static NormalFrame of(FrameIndex index, Pin pin, State state) {
        return new NormalFrame(index, pin, state);
    }

    public static NormalFrame of(FrameIndex index) {
        return of(index, new Pin(0), State.READY);
    }

    public static NormalFrame init() {
        return of(FrameIndex.first());
    }

    @Override
    public Frame bowl(Pin pin) {
        this.second = pin;
        this.state = state.bowl(this.first, pin);
        if (state.isEnd()) {
            return next();
        }
        this.first = pin;
        return this;
    }

    private Frame next() {
        if (index.next().max()) {
            return FinalFrame.init();
        }
        return of(index.next());
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    public int getFrameIndex() {
        return index.getIndex();
    }

    @Override
    public String symbol() {
        return this.state.symbol(this.first, this.second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        NormalFrame frame = (NormalFrame) obj;

        return Objects.equals(index, frame.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
