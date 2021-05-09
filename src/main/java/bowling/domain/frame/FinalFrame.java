package bowling.domain.frame;

import bowling.domain.HitNumber;
import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.state.RollResults;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static final String INVALID_ROLL = "더이상 투구할 수 없습니다.";
    private final Pin pin;
    private final RollResults results;

    private FinalFrame(Pin pin, RollResults results) {
        this.pin = pin;
        this.results = results;
    }

    public static FinalFrame of() {
        return of(Pin.of(), RollResults.of());
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
        if(isFinished()) {
            throw new IllegalStateException(INVALID_ROLL);
        }
        if (results.isCleared()) {
            return nextRoll(hitNumber, pin.reload());
        }
        return nextRoll(hitNumber, pin);
    }

    @Override
    public Frame accumulate(int score) {
        return this;
    }

    private Frame nextRoll(HitNumber hitNumber, Pin pin) {
        return of(pin, results.next(pin, hitNumber));
    }

    @Override
    public boolean isFinished() {
        return pin.isLast() || (!results.hasNext() &&!results.isCleared());
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
