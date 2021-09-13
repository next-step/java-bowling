package bowling.bowlingdrawing.domain;

import java.util.Objects;

public class Pitching {

    private final Pins pins;
    private Pitching nextPitching;

    private Pitching(Pins pins) {
        this.pins = pins;
    }

    public static Pitching of(Pins pins) {
        return new Pitching(pins);
    }

    public static Pitching of(int pins) {
        return of(new Pins(pins));
    }

    public static Pitching first(int pins) {
        return of(pins);
    }

    public Pitching next(int pins) {
        nextPitching = of(pins);
        return nextPitching;
    }

    public Integer score(int level) {
        if (level == 2) {
            return pins.pins() + nextPitching.score(1);
        }
        if (level == 1) {
            return pins.pins() + nextPitching.score(0);
        }
        return pins.pins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pitching)) return false;
        Pitching pitching = (Pitching) o;
        return Objects.equals(pins, pitching.pins) && Objects.equals(nextPitching, pitching.nextPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, nextPitching);
    }
}
