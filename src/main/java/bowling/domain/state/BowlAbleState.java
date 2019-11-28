package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.*;

public abstract class BowlAbleState implements State {
    List<Pin> pins;

    @Override
    public Score getScore(int totalStore) {
        return Score.ofNoneScore();
    }

    @Override
    public Score addBonus(Score score) {
        if (Objects.isNull(pins)) {
            return score;
        }

        for (Pin pin : pins) {
            score = score.addBonus(pin);
        }

        return score;
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
    public List<Pin> getPins() {
        if (Objects.isNull(pins)) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(pins);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public List<Pin> nextPins(int countOfPin) {
        if (Objects.isNull(this.pins)) {
            return Collections.singletonList(Pin.of(countOfPin));
        }

        List<Pin> nextPins = new ArrayList<>(this.pins);
        nextPins.add(Pin.of(countOfPin));
        return nextPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlAbleState that = (BowlAbleState) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
