package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Result;
import bowling.domain.Score;

public class Turn {

    private final Score score;
    private final Result result;
    private final Pins pins;

    public Turn(Score score, Result result, Pins pins) {
        this.score = score;
        this.result = result;
        this.pins = pins;
    }

    public static Turn from() {
        return new Turn(Score.from(), Result.READY, Pins.from());
    }
}
