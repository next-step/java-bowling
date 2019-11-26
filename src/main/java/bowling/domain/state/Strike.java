package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Strike implements State {
    private int first;

    public Strike() {
        this.first = Pin.ALL_PIN_COUNT;
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return this;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofStrike(totalScore);
    }

    @Override
    public Score addBonus(Score score) {
        return score.addBonus(first);
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(new Pin(first), new Pin(Pin.DEFAULT_PIN));
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return first == strike.first;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
