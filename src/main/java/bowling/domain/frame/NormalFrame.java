package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame{
    private static final int MAX_INDEX = 10;

    private final Pin pin;
    private final State result;

    public NormalFrame(Pin pin, State result) {
        this.pin = pin;
        this.result = result;
    }

    public static NormalFrame of() {
        return of(Pin.of(), Ready.of());
    }

    public static NormalFrame of(Pin pin, State result) {
        return new NormalFrame(pin, result);
    }

    @Override
    public Frame next(int index) {
        if(index + 1 == MAX_INDEX) {
            return FinalFrame.of();
        }
        return of();
    }

    @Override
    public Frame roll(HitNumber rollNumber) {
        return of(pin, pin.hit(result, rollNumber));
    }

    @Override
    public Frame accumulate(int score) {
        if(result.canAccumulate()) {
            return of(pin, result.next(score));
        }
        return this;
    }

    @Override
    public boolean isFinished() {
        return !result.hasNext();
    }

    @Override
    public boolean canAccumulate() {
        return result.canAccumulate();
    }

    @Override
    public Score totalScore() {
        return result.eval();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(pin, that.pin) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, result);
    }

    @Override
    public String toString() {
        return "" + result + "";
    }
}
