package bowling.domain.state.progress;

import java.util.Objects;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.Progress;
import bowling.domain.state.State;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;

public class Running implements Progress {
    BowlingPin firstPin;

    private Running(BowlingPin bowlingPin) {
        validate(bowlingPin);
        this.firstPin = bowlingPin;
    }

    private void validate(BowlingPin bowlingPin) {
        if (bowlingPin.isMax()) {
            throw new IllegalArgumentException("현재 상태에 맞지 않는 볼링핀 수 입니다.");
        }
    }

    public static Running of(BowlingPin bowlingPin) {
        return new Running(bowlingPin);
    }

    @Override
    public State bowl(BowlingPin bowlingPin) {
        if (firstPin.sum(bowlingPin).isMax()) {
            return Spare.of(firstPin, bowlingPin);
        }
        return Miss.of(firstPin, bowlingPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Running running = (Running)o;
        return Objects.equals(firstPin, running.firstPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin);
    }
}
