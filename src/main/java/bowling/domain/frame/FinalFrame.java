package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.state.RollResults;
import bowling.domain.state.State;

import java.util.Objects;

public class FinalFrame implements Frame {
    private final Pin pin;
    private final RollResults results;

    private FinalFrame(Pin pin) {
        this(pin, null);
    }

    private FinalFrame(Pin pin, RollResults results) {
        this.pin = pin;
        this.results = results;
    }

    public static FinalFrame of() {
        return new FinalFrame(Pin.of());
    }

    public static FinalFrame of(Pin pin) {
        return new FinalFrame(pin);
    }

    public static FinalFrame of(RollResults results) {
        return new FinalFrame(null, results);
    }

    public static FinalFrame of(Pin pin, RollResults results) {
        return new FinalFrame(pin, results);
    }

    @Override
    public Frame next(int index) {
        return this;
    }

    @Override
    public Frame roll(HitNumber hitNumber) {
        if (results == null) {
            return firstRoll(hitNumber, this.pin);
        }
        if (!results.isCleared() && !results.hasNext()) {
            throw new IllegalStateException();
        }
        if (results.isCleared()) {
            return nextRoll(hitNumber, Pin.reload(pin));
        }
        return nextRoll(hitNumber, pin);
    }

    @Override
    public Frame addScore(int score) {
        return this;
    }

    private Frame firstRoll(HitNumber hitNumber, Pin pin) {
        State type = pin.firstHit(hitNumber);
        return of(pin, RollResults.of(type));
    }

    private Frame nextRoll(HitNumber hitNumber, Pin pin) {
        return of(pin, results.next(pin, hitNumber));
    }

    @Override
    public boolean isFinished() {
        return pin.isLast() || (results != null && !results.isCleared() && !results.hasNext());
    }

    @Override
    public boolean canAccumulate() {
        return !isFinished();
    }

    @Override
    public Score totalScore() {
        return results.eval();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(pin, that.pin) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, results);
    }

    @Override
    public String toString() {
        return "" + results + "";
    }
}
