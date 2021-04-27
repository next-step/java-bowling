package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

import java.util.Objects;

public final class NullPins implements Pins {

    private final FrameStatus frameStatus = FrameStatus.NOT_ENDED;

    @Override
    public Pin firstPin() {
        return null;
    }

    @Override
    public Pin secondPin() {
        return null;
    }

    @Override
    public FrameStatus frameStatus() {
        return frameStatus;
    }

    @Override
    public Pins knockDownPin(Pin pin) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NullPins nullPins = (NullPins) o;
        return frameStatus == nullPins.frameStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameStatus);
    }
}
