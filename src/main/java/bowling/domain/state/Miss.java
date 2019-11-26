package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Miss implements State {
    private int first;
    private int second;

    public Miss(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return this;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.of(totalScore, first + second);
    }

    @Override
    public Score addBonus(Score score) {
        score = score.addBonus(first);
        return score.addBonus(second);
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(new Pin(first), new Pin(second));
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return first == miss.first &&
                second == miss.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
