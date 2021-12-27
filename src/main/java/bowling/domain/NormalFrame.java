package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameIndex index;
    private final Bowl bowl;


    private NormalFrame(FrameIndex index, Bowl bowl) {
        this.index = index;
        this.bowl = bowl;
    }

    public static NormalFrame of(FrameIndex index, Bowl bowl) {
        return new NormalFrame(index, bowl);
    }

    public static NormalFrame of(FrameIndex index) {
        return of(index, Bowl.first());
    }

    public static NormalFrame init() {
        return of(FrameIndex.first());
    }

    @Override
    public Frame bowl(Pin pin) {
        bowl.bowl(pin);
        if (isEnd()) {
            return next();
        }
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
        return bowl.getLastState().isEnd();
    }

    public int getFrameIndex() {
        return index.getIndex();
    }

    @Override
    public String symbol() {
        return bowl.symbol();
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
