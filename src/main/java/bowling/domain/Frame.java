package bowling.domain;

import java.util.Objects;
import java.util.Optional;

public class Frame {

    private Frame nextFrame;
    private Frame beforeFrame;
    private Pins pins = new Pins();
    private HitState hitState = HitState.START;
    private Score firstScore;
    private Score secondScore;

    private Frame() { }

    private Frame(Frame beforeFrame, Frame nextFrame) {
        this.beforeFrame = beforeFrame;
        this.nextFrame = nextFrame;
    }

    public static Frame of() {
        return new Frame();
    }

    public Frame createNext() {
        this.nextFrame = new Frame(this, null);
        return nextFrame;
    }

    public Frame createBefore() {
        this.beforeFrame = new Frame(null, this);
        return beforeFrame;
    }

    public void doFirstShot(int hitCount) {
        hitPins(hitCount);
        firstScore = new Score(hitCount);
    }

    public void doSecondShot(int hitCount) {
        hitPins(hitCount);
        secondScore = new Score(hitCount);
    }

    private void hitPins(int hitCount) {
        pins.hit(hitCount);
    }

    public boolean isHitAll() {
        return pins.isHitAll();
    }

    public int remainedPins() {
        return pins.standing();
    }

    public Score getFirstScore() {
        return Optional.ofNullable(firstScore)
            .orElse(new Score());
    }

    public Score getSecondScore() {
        return Optional.ofNullable(secondScore)
            .orElse(new Score());
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
        return Objects.equals(nextFrame, frame.nextFrame) && Objects.equals(beforeFrame, frame.beforeFrame) && Objects.equals(pins, frame.pins) && hitState == frame.hitState
            && Objects.equals(firstScore, frame.firstScore) && Objects.equals(secondScore, frame.secondScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, hitState, firstScore, secondScore);
    }

    @Override
    public String toString() {
        return "{" + firstScore + ", " + secondScore + "}";
    }
}
