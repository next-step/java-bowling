package bowling.domain.frame;

import java.util.Objects;

public final class Frame {

    private final RoundNumber roundNumber;
    private final FrameScore frameScore;

    public Frame(RoundNumber roundNumber, FrameScore frameScore) {
        this.roundNumber = roundNumber;
        this.frameScore = frameScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(roundNumber, frame.roundNumber) && Objects.equals(frameScore, frame.frameScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundNumber, frameScore);
    }
}
