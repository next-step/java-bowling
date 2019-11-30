package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.Collections;

public class Strike extends EndState {
    public Strike() {
        this.pins = Collections.singletonList(Pin.ofAllPin());
    }

    @Override
    public Score getScore(int totalScore) {
        return Score.ofStrike(totalScore);
    }
}
