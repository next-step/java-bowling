package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.Objects;

public class RegularPitching {
    private final Pitching firstPitching;
    private Pitching secondPitching;

    public RegularPitching(Pitching firstPitching, Pitching secondPitching) {
        validateSumIsOverTen(firstPitching, secondPitching);
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
    }

    private void validateSumIsOverTen(Pitching firstPitching, Pitching secondPitching) {
        if (firstPitching.sum(secondPitching) > Pitching.MAXIMUM_OF_PINS) {
            throw new CustomException("frame 최대 pins 수 초과");
        }
    }

    public RegularPitching(Pitching firstPitching) {
        this(firstPitching, null);
    }

    public void secondPitch(Pitching secondPitching) {
        if (this.secondPitching == null) {
            validateSumIsOverTen(this.firstPitching, secondPitching);
            this.secondPitching = secondPitching;
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
        if (!(o instanceof RegularPitching)) return false;
        RegularPitching that = (RegularPitching) o;
        return Objects.equals(firstPitching, that.firstPitching) && Objects.equals(secondPitching, that.secondPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPitching, secondPitching);
    }
}