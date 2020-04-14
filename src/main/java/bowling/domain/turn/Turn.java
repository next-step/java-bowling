package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Result;
import bowling.domain.Score;

import java.util.Objects;

public class Turn {

    private final Score score;
    private final Result result;
    private final Pins pins;
    private final TurnState turnState;

    public Turn(final Score score, final Result result,
                final Pins pins, final TurnState turnState) {
        this.score = score;
        this.result = result;
        this.pins = pins;
        this.turnState = turnState;
    }

    public static Turn from() {
        return new Turn(Score.from(), Result.READY, Pins.from(), TurnState.FIRST);
    }

    public Turn bowl(final int pinCount) {
        Pins pin = pins.bowl(pinCount);
        Score score = pin.getScore();
        TurnState nextTurnState = turnState.getNextTurnState();
        Result resultState = pin.getResultState(turnState);

        return new Turn(score, resultState, pin, nextTurnState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return Objects.equals(score, turn.score) &&
                result == turn.result &&
                Objects.equals(pins, turn.pins) &&
                turnState == turn.turnState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, result, pins, turnState);
    }
}
