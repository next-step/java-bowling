package bowling;

import java.util.Objects;

public class Frame {

    private final Pitchings pitchings;
    private Frame nextFrame;

    private Frame(Pitchings pitchings) {
        this.pitchings = pitchings;
    }

    public Frame getNextFrame() {
        return this.nextFrame;
    }

    public boolean isLastFrame() {
        return Objects.isNull(nextFrame);
    }

    public void bowl(Pin pin) {
        pitchings.bowl(pin);
    }

    public Pin getFirstPin() {
        return pitchings.getFirstPin();
    }

    public Pin getSecondPin() {
        return pitchings.getSecondPin();
    }

    public Pin getBonusPin() {
        return pitchings.getBonusPin();
    }

    public boolean isFirstDone() {
        return pitchings.isFirstDone();
    }

    public boolean isSecondDone() {
        return pitchings.isSecondDone();
    }

    public boolean isBonusDone() {
        return pitchings.isBonusDone();
    }

    public boolean isDone() {
        return pitchings.isDone();
    }

    public Frame next() {
        this.nextFrame = of();
        return this.nextFrame;
    }

    public void last() {
        this.nextFrame = new Frame(LastPitchings.of());
    }

    public static Frame of() {
        return new Frame(NormalPitchings.ofReady());
    }
}
