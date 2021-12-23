package bowling.domain;

import java.util.Objects;

public class NormalFrame {

    public static final int INITIALIZED_TO_ZERO = 0;
    public static final int COUNT_OF_TEN_PINS = 10;

    private final Frame frame;
    private final Pitching countOfPitching;
    private final BowlingPins upPins;

    public NormalFrame() {
        this(INITIALIZED_TO_ZERO, COUNT_OF_TEN_PINS);
    }

    public NormalFrame(Frame nextFrame) {
        this(nextFrame, INITIALIZED_TO_ZERO, COUNT_OF_TEN_PINS);
    }

    public NormalFrame(int countOfPitching, int upPins) {
        this(new Frame(INITIALIZED_TO_ZERO), countOfPitching, upPins);
    }

    public NormalFrame(Frame frame, int countOfPitching, int upPins) {
        this.frame = frame;
        this.countOfPitching = new Pitching(countOfPitching);
        this.upPins = new BowlingPins(upPins);
    }

    public Pitching plusCount() {
        return this.countOfPitching.plusCount();
    }

    public BowlingPins downPins(int countOfHits) {
        return this.upPins.down(countOfHits);
    }

    public NormalFrame createNextFrame(Pitching currentPitching) {
        if (currentPitching.isSecondPitching()) {
            return new NormalFrame(this.frame.nextFrame());
        }
        return this;
    }

    public Frame getFrame() {
        return frame;
    }

    public Pitching getCountOfPitching() {
        return countOfPitching;
    }

    public BowlingPins getUpPins() {
        return upPins;
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
        return Objects.equals(countOfPitching, that.countOfPitching) && Objects.equals(upPins, that.upPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPitching, upPins);
    }

}
