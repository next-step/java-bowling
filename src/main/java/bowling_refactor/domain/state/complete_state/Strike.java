package bowling_refactor.domain.state.complete_state;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.Score;

import java.util.Arrays;

public class Strike extends Complete {

    public Strike() {
        this.pins = Arrays.asList(Pin.of(Pin.MAX_PIN));
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofStrike(totalScore);
    }
}
