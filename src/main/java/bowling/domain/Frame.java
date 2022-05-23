package bowling.domain;

import java.util.Objects;

public class Frame {

    private Frame nextFrame;
    private Frame beforeFrame;
    protected Pins pins = new Pins();
    protected Scores scores = new Scores();

    public Frame() {
        this(null, null);
    }

    protected Frame(Frame beforeFrame, Frame nextFrame) {
        this.beforeFrame = beforeFrame;
        this.nextFrame = nextFrame;
    }

    public Frame createNext() {
        this.nextFrame = new Frame(this, null);
        return nextFrame;
    }

    public void createFinal() {
        this.nextFrame = new FinalFrame(this, null);
    }

    public Frame createBefore() {
        this.beforeFrame = new Frame(null, this);
        return beforeFrame;
    }

    public void shot(int hitCount) {
        pins.hit(hitCount);
        scores.hit(hitCount);
    }

    public boolean isDone() {
        return pins.isHitAll() || scores.isHitTwice();
    }

    public int remainedPins() {
        return pins.standing();
    }

    public Score getFirstScore() {
        return scores.first();
    }

    public Score getSecondScore() {
        return scores.second();
    }

    public Frame next() {
        return this.nextFrame;
    }

    public Frame before() {
        return this.beforeFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return Objects.equals(nextFrame, frame.nextFrame) && Objects.equals(beforeFrame, frame.beforeFrame) && Objects.equals(pins, frame.pins) && Objects.equals(
            scores, frame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextFrame, beforeFrame, pins, scores);
    }
}
