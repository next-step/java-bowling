package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FirstBowl implements State {
    private int first;

    public FirstBowl(int first) {
        this.first = first;
    }

    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        int sum = this.first + countOfPin;

        if (isLastFrame) {
            return new SecondBowl(this.first, countOfPin);
        }

        if (sum == Pin.ALL_PIN_COUNT) {
            return new Spare(this.first, countOfPin);
        }

        return new Miss(this.first, countOfPin);
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofNoneScore();
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
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return first == firstBowl.first;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
