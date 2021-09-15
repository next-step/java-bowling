package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pins;
import bowling.bowlingdrawing.domain.pitching.Pitching;
import bowling.bowlingdrawing.exception.CustomException;

import java.util.Objects;

public class Frame {
    private final Pitching firstPitching;
    private Pitching secondPitching;

    public Frame(Pitching firstPitching, Pitching secondPitching) {
        validateSumIsOverTen(firstPitching, secondPitching);
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
    }

    public Frame(Pitching firstPitching) {
        this(firstPitching, null);
    }

    private void validateSumIsOverTen(Pitching firstPitching, Pitching secondPitching) {
        if (secondPitching == null) {
            return;
        }

        if (firstPitching.sum(secondPitching) > Pins.MAXIMUM_PINS) {
            throw new CustomException("Frame 전체 pin 개수가 10개를 초과합니다.");
        }
    }

    public Integer score() {
        if (strike()) {
            return firstPitching.score(Pitching.SCORE_LEVEL_OF_STRIKE);
        }

        if (spare()) {
            return firstScore() + secondPitching.score(Pitching.SCORE_LEVEL_OF_SPARE);
        }

        return firstPitching.sum(secondPitching);
    }

    public boolean strike() {
        return firstPitching.score(0) == Pins.MAXIMUM_PINS;
    }

    public boolean spare() {
        if (secondPitching == null) {
            return false;
        }
        return firstPitching.sum(secondPitching) == Pins.MAXIMUM_PINS;
    }

    public Integer firstScore() {
        return firstPitching.score(Pitching.SCORE_LEVEL_OF_MISS);
    }

    public Integer secondScore() {
        if (secondPitching == null) {
            return Pitching.IS_NULL;
        }
        return secondPitching.score(Pitching.SCORE_LEVEL_OF_MISS);
    }

    public void pitch(Pitching pitching) {
        validateSumIsOverTen(firstPitching, pitching);
        this.secondPitching = pitching;
    }

    public boolean done() {
        return strike() || (secondPitching != null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frame)) return false;
        Frame frame = (Frame) o;
        return Objects.equals(firstPitching, frame.firstPitching) && Objects.equals(secondPitching, frame.secondPitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPitching, secondPitching);
    }
}
