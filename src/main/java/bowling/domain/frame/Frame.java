package bowling.domain.frame;

import bowling.domain.pin.BallThrows;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.Objects;

public abstract class Frame {

    protected final RoundNumber roundNumber;
    protected final BallThrows ballThrows;
    protected Frame nextFrame;

    protected Frame(RoundNumber roundNumber, BallThrows ballThrows) {
        this.roundNumber = roundNumber;
        this.ballThrows = ballThrows;
    }

    public abstract Frame nextFrame();

    public abstract void createNextFrame();

    public abstract void knockDownPin(Pin pin);

    public abstract boolean isEnded();

    public abstract boolean isFinalFrame();

    public abstract Score score();

    protected abstract Score addScore(Score previousScore);

    public BallThrows pins() {
        return ballThrows;
    }

    public boolean isNotStarted() {
        return ballThrows.isFirstThrow();
    }

    public boolean roundNumberEquals(RoundNumber roundNumber) {
        return this.roundNumber.equals(roundNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(roundNumber, frame.roundNumber) && Objects.equals(ballThrows, frame.ballThrows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundNumber, ballThrows);
    }
}
