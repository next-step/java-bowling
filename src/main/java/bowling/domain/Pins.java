package bowling.domain;

import bowling.domain.turn.TurnState;
import bowling.exception.BowlingException;

public class Pins {

    private static final String PINS_COUNT_RANGE = "핀은 0~10 사이여야 합니다.";
    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;

    private final int pins;

    public Pins(int pins) {
        validatePinesCount(pins);
        this.pins = pins;
    }

    public static Pins from() {
        return new Pins(MAX_PIN);
    }

    private void validatePinesCount(int pins) {
        if (pins < MIN_PIN || pins > MAX_PIN) {
            throw new BowlingException(PINS_COUNT_RANGE);
        }
    }

    public Pins bowl(final int count) {
        return new Pins(this.pins - count);
    }

    public Score getScore(final Score current) {
        Score pinScore = new Score(getDownPin());
        return current.addScore(pinScore);
    }

    public Result getResultState(TurnState turnState) {
        return Result.getResultState(turnState, pins);
    }

    private int getDownPin() {
        return MAX_PIN - this.pins;
    }
}
