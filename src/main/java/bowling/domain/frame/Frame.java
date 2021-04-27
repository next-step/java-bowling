package bowling.domain.frame;

import bowling.domain.pin.Pin;

import java.util.Objects;

public abstract class Frame {

    protected final RoundNumber roundNumber;
    protected final FrameScore frameScore;

    protected Frame(RoundNumber roundNumber, FrameScore frameScore) {
        this.roundNumber = roundNumber;
        this.frameScore = frameScore;
    }

    public abstract boolean isFinalFrame();

    public abstract Frame createNextFrame();

    public abstract void knockDownPin(Pin pin);

    public abstract String status();

    public boolean isEnded() {
        return frameScore.isEnded();
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
