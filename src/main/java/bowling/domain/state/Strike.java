package bowling.domain.state;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;

public class Strike implements Finished {

    private static final int STRIKE_LEFT_COUNT = 2;

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

    @Override
    public Score getScore() {
        return Score.of(Pins.MAX_PIN_NUMBER, STRIKE_LEFT_COUNT);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(Pins.MAX_PIN_NUMBER);
    }

    @Override
    public String toString() {
        return "X";
    }
}
