package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.Objects;

public class Frame {
    private final Pitching firstPitching;
    private Pitching secondPitching;

    private Frame(Pitching firstPitching, Pitching secondPitching) {
        validateSumIsOverTen(firstPitching, secondPitching);
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
    }

    private void validateSumIsOverTen(Pitching firstPitching, Pitching secondPitching) {
        if (secondPitching == null) {
            return;
        }

        if (firstPitching.sum(secondPitching) > 10) {
            throw new CustomException("Frame 전체 pin 개수가 10개를 초과합니다.");
        }
    }

    public static Frame of(Pitching firstPitching, Pitching secondPitching) {
        return new Frame(firstPitching, secondPitching);
    }

    public static Frame of(Pitching firstPitching) {
        return of(firstPitching, null);
    }

    public void secondPitching(Pitching secondPitching) {
        this.secondPitching = secondPitching;
    }

    public Integer score() {
        if (strike()) {
            return firstPitching.score(2);
        }

        if (spare()) {
            return firstPitching.score(0) + secondPitching.score(1);
        }

        return firstPitching.score(1);
    }

    public boolean strike() {
        return firstPitching.score(0) == 10;
    }

    public boolean spare() {
        if (secondPitching == null) {
            return false;
        }
        return firstPitching.sum(secondPitching) == 10;
    }

    public boolean done() {
        return strike() || (secondPitching != null);
    }

    public boolean end() {
        if (strike() && firstPitching.score(2) >= 10) {
            return true;
        }

        if (spare() && secondPitching.score(1) >= 0) {
            return true;
        }

        return secondPitching != null;
    }

    public Integer firstScore() {
        return firstPitching.score(0);
    }

    public Integer secondScore() {
        if (secondPitching == null) {
            return -1;
        }
        return secondPitching.score(0);
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
