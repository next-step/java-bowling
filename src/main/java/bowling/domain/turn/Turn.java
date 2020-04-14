package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Score;

import java.util.Objects;

public class Turn {

    private final Score score;
    private final Pins pins;
    private final TurnState turnState;

    public Turn(final Score score, final Pins pins, final TurnState turnState) {
        this.score = score;
        this.pins = pins;
        this.turnState = turnState;
    }

    public static Turn from(final int pinCount) {
        Pins pin = Pins.from()
                .bowl(pinCount);

        return new Turn(pin.getScore(), pin, TurnState.FIRST);
    }

    public Turn bowl(final int pinCount) {
        Pins pin = pins.bowl(pinCount);
        Score score = pin.getScore();
        TurnState nextTurnState = turnState.getNextTurnState();

        return new Turn(score, pin, nextTurnState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return Objects.equals(score, turn.score) &&
                Objects.equals(pins, turn.pins) &&
                turnState == turn.turnState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, pins, turnState);
    }
}
