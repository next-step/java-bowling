package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Spare implements State {
    private Pin first;
    private Pin second;

    public Spare(int first, int second) {
        this(Pin.of(first), second);
    }

    public Spare(Pin first, int second) {
        this.first = first;
        this.second = Pin.of(second);
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return this;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofSpare(totalScore);
    }

    @Override
    public Score addBonus(Score score) {
        score = score.addBonus(first);
        return score.addBonus(second);
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(first, second);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return first == spare.first &&
                second == spare.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
