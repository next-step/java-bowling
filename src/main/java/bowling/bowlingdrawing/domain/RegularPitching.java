package bowling.bowlingdrawing.domain;

import java.util.Objects;

public class RegularPitching {
    private final Pitching firstPitching;
    private final Pitching secondPitching;

    public RegularPitching(Pitching firstPitching, Pitching secondPitching) {
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
    }

    public RegularPitching(Pitching firstPitching) {
        this(firstPitching, null);
    }

    public boolean strike() {
        return false;
    }

    public boolean spare() {
        return false;
    }

    public int score() {
        return 0;
    }

    public void secondPitch(int pinsSecond) {
    }

    public boolean done() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegularPitching)) return false;
        RegularPitching that = (RegularPitching) o;
        return Objects.equals(firstPitching, that.firstPitching) && Objects.equals(secondPitching, that.secondPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPitching, secondPitching);
    }
}
