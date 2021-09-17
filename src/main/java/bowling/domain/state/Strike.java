package bowling.domain.state;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public class Strike extends Finished {
    public Strike() {
        firstPin = new Pin(MAX_PIN_NO);
        score = Score.ofStrike();
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }
}
