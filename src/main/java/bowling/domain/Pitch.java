package bowling.domain;

import bowling.domain.status.*;

import java.util.Objects;

public class Pitch {
    private final int fallenPins;
    private Status status;

    public Pitch(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public Pitch(int fallenPins, Status status) {
        this.fallenPins = fallenPins;
        this.status = status;
    }

    public Pitch pitch(int fallenPins, int pitchIndex) {
        if ((fallenPins == 10 && pitchIndex == 0)
                || (this.fallenPins + fallenPins == 20)
                || (fallenPins == 10 && pitchIndex == 2)) {
            return new Pitch(fallenPins, new Strike());
        }
        if (this.fallenPins + fallenPins == 10) {
            return new Pitch(fallenPins, new Spare());
        }
        if (this.fallenPins + fallenPins < 10 && pitchIndex == 1) {
            return new Pitch(fallenPins, new Open());
        }
        return new Pitch(fallenPins, new Default());
    }

    public boolean isStrike() {
        return status.isStrike();
    }

    public boolean isSpare() {
        return status.isSpare();
    }

    public boolean isOpen() {
        return status.isOpen();
    }

    public static Pitch sum(Pitch previous, Pitch current) {
        return new Pitch(previous.intValue() + current.intValue());
    }

    public int intValue() {
        return fallenPins;
    }

    public Status status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return fallenPins == pitch.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }
}
