package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EndState implements State {
    List<Pin> pins;

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return this;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.of(totalScore, sum());
    }

    @Override
    public int sum() {
        if (Objects.isNull(pins)) {
            return 0;
        }

        return pins.stream()
                .mapToInt(Pin::getPin)
                .sum();
    }

    @Override
    public Score addBonus(Score score) {
        for (Pin pin : pins) {
            score = score.addBonus(pin);
        }

        return score;
    }

    @Override
    public List<Pin> getPins() {
        if (Objects.isNull(pins)) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(pins);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndState endState = (EndState) o;
        return Objects.equals(pins, endState.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
