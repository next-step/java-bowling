package bowling_refactor.domain.state.complete_state;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.Score;
import bowling_refactor.domain.state.State;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Complete implements State {

    List<Pin> pins;

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return this;
    }

    @Override
    public int sum() {
        if (emptyPin()) {
            return 0;
        }
        return pins.stream()
                .mapToInt(Pin::getPin)
                .sum();
    }

    @Override
    public List<Pin> getPins() {
        if (emptyPin()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(pins);
    }

    @Override
    public Score addBonus(Score score) {
        for (Pin pin : pins) {
            score = score.addBonus(pin);
        }
        return score;
    }

    @Override
    public Score getScore(int totalStore) {
        return Score.of(sum(), totalStore);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    private boolean emptyPin() {
        return Objects.isNull(pins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complete complete = (Complete) o;
        return Objects.equals(pins, complete.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
