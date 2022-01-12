package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.frame.Score;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.exception.CannotScoreCalculateException;

import java.util.Objects;

public class FirstBowl extends RunningState {

    private final Pins pins;

    private FirstBowl(Pins pins) {
        this.pins = pins;
    }

    public static ThrowingState create(Pins pins) {
        return new FirstBowl(pins);
    }

    @Override
    public ThrowingState bowl(Pins secondPins) {
        validate(secondPins);
        if (pins.isSpare(secondPins)) {
            return Spare.create(pins, secondPins);
        }
        return Miss.create(pins, secondPins);
    }

    @Override
    public String symbol() {
        return String.valueOf(pins.getPinCount());
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) throws CannotScoreCalculateException {
        beforeScore = pins.sumScore(beforeScore);
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        throw new CannotScoreCalculateException("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
    }

    private void validate(Pins secondPins) {
        if (pins.totalPinsCount(secondPins) > Pins.MAX_RANGE) {
            throw new IllegalArgumentException(String.format("넘어트린 핀의 총 합은 %d개보다 클 수 없습니다. 현재 %d개 입니다.", Pins.MAX_RANGE, pins.totalPinsCount(secondPins)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(pins, firstBowl.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

    @Override
    public String toString() {
        return "FirstBowl{" +
                "pins=" + pins +
                '}';
    }
}
