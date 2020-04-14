package bowling.domain.turn;

import bowling.domain.Pins;
import bowling.domain.Score;

import java.util.Objects;

public class Turn {

    private final Score score;
    private final Pins pins;

    public Turn(final Score score, final Pins pins) {
        this.score = score;
        this.pins = pins;
    }

    public static Turn from(final int pinCount) {
        Pins pin = Pins.from()
                .bowl(pinCount);

        return new Turn(pin.getScore(), pin);
    }

    public Turn bowl(final int pinCount) {
        Pins pin = pins.bowl(pinCount);
        Score score = pin.getScore();

        return new Turn(score, pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turn turn = (Turn) o;
        return Objects.equals(score, turn.score) &&
                Objects.equals(pins, turn.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, pins);
    }
}
