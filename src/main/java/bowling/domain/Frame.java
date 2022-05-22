package bowling.domain;

import java.util.Objects;

public class Frame {

    private Frame nextFrame;
    private Frame beforeFrame;
    private Pins pins = new Pins();
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

    public void shot(int hitCount) {
        if (firstScore == null) {
            shotFirst(hitCount);
            return;
        }

        shotSecond(hitCount);
    }

    public void finalShot(int hitCount) {
        shot(hitCount);

        if (isStrike() || isSpare()) {
            createNext();
        }
    }

    private boolean isStrike() {
        return firstScore != null && firstScore.get() == 0;
    }

    private boolean isSpare() {
        return firstScore != null && secondScore != null && firstScore.get() + secondScore.get() == 10;
    }

    private void shotFirst(int hitCount) {
        hitPins(hitCount);
        firstScore = new Score(hitCount);
    }

    private void shotSecond(int hitCount) {
        hitPins(hitCount);
        secondScore = new Score(hitCount);
    }

    private void hitPins(int hitCount) {
        pins.hit(hitCount);
    }

    public boolean isDone() {
        return pins.isHitAll() || (firstScore != null && secondScore != null);
    }

    public int remainedPins() {
        return pins.standing();
    }

    public Score getFirstScore() {
        return firstScore;
    }

    public Score getSecondScore() {
        return secondScore;
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
        return Objects.equals(nextFrame, frame.nextFrame) && Objects.equals(beforeFrame, frame.beforeFrame) && Objects.equals(pins, frame.pins)
            && Objects.equals(firstScore, frame.firstScore) && Objects.equals(secondScore, frame.secondScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, firstScore, secondScore);
    }

    @Override
    public String toString() {
        return "{" + firstScore + ", " + secondScore + "}";
    }

}
