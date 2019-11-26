package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Last implements State {
    private Pin first;
    private Pin second;
    private Pin third;

    public Last(int first, int second, int third) {
        this(Pin.of(first), Pin.of(second), third);
    }

    public Last(Pin first, Pin second, int third) {
        this.first = first;
        this.second = second;
        this.third = Pin.of(third);
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return this;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.of(totalScore, first.sum(second, third));
    }

    @Override
    public Score addBonus(Score score) {
        score = score.addBonus(first);
        score = score.addBonus(second);
        return score.addBonus(third);
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(first, second, third);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Last last = (Last) o;
        return first == last.first &&
                second == last.second &&
                third == last.third;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }
}
