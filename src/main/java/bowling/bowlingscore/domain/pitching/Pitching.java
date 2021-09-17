package bowling.bowlingscore.domain.pitching;

import java.util.Objects;

public class Pitching {
    
    public static final int IS_NULL = -1;

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

    public int scoreToNextTwoPitching() {
        if (nextPitching == null || nextPitching.nextPitching == null) {
            return IS_NULL;
        }

        return pins.pins() +  nextPitching.pins.pins() + nextPitching.nextPitching.pins.pins();
    }

    public int scoreToNextPitching() {
        if (nextPitching == null) {
            return IS_NULL;
        }
        return pins.pins() + nextPitching.pins.pins();
    }

    public int pins() {
        return pins.pins();
    }

    public int sum(Pitching pitching) {
        if (pitching == null) {
            return IS_NULL;
        }
        return pins.pins() + pitching.pins.pins();
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
