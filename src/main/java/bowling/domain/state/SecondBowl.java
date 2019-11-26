package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SecondBowl implements State {
    private Pin first;
    private Pin second;

    public SecondBowl(int first, int second) {
        this(Pin.of(first), second);
    }

    public SecondBowl(Pin first, int second) {
        this.first = first;
        this.second = Pin.of(second);
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        return new Last(this.first, this.second, countOfPin);
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofNoneScore();
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
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondBowl that = (SecondBowl) o;
        return first == that.first &&
                second == that.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
