package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Arrays;
import java.util.List;

public class Ready implements State {
    @Override
    public State bowl(int countOfPin, boolean isLastFrame) {
        if (!isLastFrame && countOfPin == Pin.ALL_PIN_COUNT) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofNoneScore();
    }

    @Override
    public Score addBonus(Score score) {
        return score;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(new Pin(Pin.DEFAULT_PIN), new Pin(Pin.DEFAULT_PIN));
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }
}
