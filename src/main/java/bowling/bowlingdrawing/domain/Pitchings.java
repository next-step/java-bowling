package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.Objects;

public class Pitchings {
    private Pitching firstPitching;
    private Pitching secondPitching;

    public Pitchings() {}

    public Pitchings(Pitching firstPitching, Pitching secondPitching) {
        validateSumIsOverTen(firstPitching, secondPitching);
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
    }

    public Pitchings(int firstPitchingPins, int secondPitchingPins) {
        this(new Pitching(firstPitchingPins), new Pitching(secondPitchingPins));
    }

    private void validateSumIsOverTen(Pitching firstPitching, Pitching secondPitching) {
        if (firstPitching.sum(secondPitching) > Pitching.MAXIMUM_OF_PINS) {
            throw new CustomException("frame 최대 pins 수 초과");
        }
    }

    public Pitchings(Pitching firstPitching) {
        this(firstPitching, null);
    }

    public Pitchings(int firstPitchingPins) {
        this(new Pitching(firstPitchingPins), null);
    }

    public void pitching(Pitching pitching) {
        if (this.secondPitching == null) {
            validateSumIsOverTen(this.firstPitching, pitching);
            this.secondPitching = pitching;
            return;
        }
        throw new CustomException("이미 완료된 Pitching");
    }

    public boolean strike() {
        return firstPitching.isTen();
    }

    public boolean spare() {
        return firstPitching.sum(secondPitching) == Pitching.MAXIMUM_OF_PINS;
    }

    public int score() {
        return firstPitching.sum(secondPitching);
    }

    public boolean done() {
        return secondPitching != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pitchings)) return false;
        Pitchings that = (Pitchings) o;
        return Objects.equals(firstPitching, that.firstPitching) && Objects.equals(secondPitching, that.secondPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPitching, secondPitching);
    }
}
