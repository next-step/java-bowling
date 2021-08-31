package bowling.domain.state;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;

public class Gutter implements Finished {

    private Gutter() {
    }

    public static Gutter of() {
        return new Gutter();
    }

    @Override
    public Score getScore() {
        return Score.of(Pins.MIN_PIN_NUMBER, 0);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        if (score.canCalculateScore()) {
            return score;
        }

        return score.bowl(Pins.MIN_PIN_NUMBER);
    }

    @Override
    public String toString() {
        return "-";
    }
}
