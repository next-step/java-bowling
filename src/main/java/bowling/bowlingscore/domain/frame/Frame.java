package bowling.bowlingscore.domain.frame;

import bowling.bowlingscore.domain.pitching.Pins;
import bowling.bowlingscore.domain.pitching.Pitching;
import bowling.bowlingscore.exception.CustomException;

import java.util.Objects;

public class Frame {
    private final Pitching firstPitching;
    private Pitching secondPitching;
    private final Frame beforeFrame;

    public Frame(Pitching firstPitching, Pitching secondPitching, Frame beforeFrame) {
        validateSumIsOverTen(firstPitching, secondPitching);
        this.firstPitching = firstPitching;
        this.secondPitching = secondPitching;
        this.beforeFrame = beforeFrame;
    }

    public Frame(Pitching firstPitching, Pitching secondPitching) {
        this(firstPitching, secondPitching, null);
    }

    public Frame(Pitching firstPitching) {
        this(firstPitching, null, null);
    }

    public Frame(Pitching firstPitching, Frame beforeFrame) {
        this(firstPitching, null, beforeFrame);
    }

    private void validateSumIsOverTen(Pitching firstPitching, Pitching secondPitching) {
        if (secondPitching == null) {
            return;
        }

        if (firstPitching.sum(secondPitching) > Pins.MAXIMUM_PINS) {
            throw new CustomException("Frame 전체 pin 개수가 10개를 초과합니다.");
        }
    }

    public void pitch(Pitching pitching) {
        validateSumIsOverTen(firstPitching, pitching);
        this.secondPitching = pitching;
    }

    public boolean done() {
        return strike() || (secondPitching != null);
    }

    public int totalScore() {
        if (score() == Pitching.IS_NULL) {
            return Pitching.IS_NULL;
        }

        if (beforeFrame == null) {
            return score();
        }

        return score() + beforeFrame.totalScore();
    }

    public int score1() {
        return 0;
    }

    public int score() {
        if (strike()) {
            return strikeScore();
        }

        if (spare()) {
            return spareScore();
        }

        return firstPitching.sum(secondPitching);
    }

    public boolean strike() {
        return firstPitching.score(0) == Pins.MAXIMUM_PINS;
    }

    private int strikeScore() {
        return firstPitching.score(Pitching.SCORE_LEVEL_OF_STRIKE);
    }

    public boolean spare() {
        if (secondPitching == null) {
            return false;
        }
        return firstPitching.sum(secondPitching) == Pins.MAXIMUM_PINS;
    }

    private int spareScore() {
        if (secondPitching.score(Pitching.SCORE_LEVEL_OF_SPARE) == Pitching.IS_NULL) {
            return Pitching.IS_NULL;
        }
        return firstScore() + secondPitching.score(Pitching.SCORE_LEVEL_OF_SPARE);
    }

    public int firstScore() {
        return firstPitching.score(Pitching.SCORE_LEVEL_OF_MISS);
    }

    public int secondScore() {
        if (secondPitching == null) {
            return Pitching.IS_NULL;
        }
        return secondPitching.score(Pitching.SCORE_LEVEL_OF_MISS);
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
