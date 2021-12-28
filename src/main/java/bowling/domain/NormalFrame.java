package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameIndex index;
    private final Balls balls;


    private NormalFrame(FrameIndex index, Balls pins) {
        this.index = index;
        this.balls = pins;
    }

    public static NormalFrame of(FrameIndex index, Balls pins) {
        return new NormalFrame(index, pins);
    }

    public static NormalFrame next(FrameIndex index) {
        return of(index, Balls.init());
    }

    public static NormalFrame init() {
        return next(FrameIndex.first());
    }

    @Override
    public Frame bowl(Ball ball) {
        balls.bowl(ball);
        if (isEnd()) {
            return next();
        }
        return this;
    }

    private Frame next() {
        if (index.next().max()) {
            return FinalFrame.init();
        }
        return next(index.next());
    }

    @Override
    public boolean isEnd() {
        return balls.isEnd();
    }

    public int getFrameIndex() {
        return index.getIndex();
    }

    @Override
    public String symbol() {
        return balls.symbol();
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
