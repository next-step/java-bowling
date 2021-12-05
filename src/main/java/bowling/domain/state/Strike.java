package bowling.domain.state;

import bowling.domain.value.Pins;

public class Strike implements State {
    private static final String STRIKE = "X";

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

    @Override
    public State pitch(Pins pins) {
        throw new IllegalArgumentException(INVALID_PITCH);
    }

    @Override
    public Score calculateScore() {
        return Score.ofStrike();
    }

    @Override
    public String mark() {
        return STRIKE;
    }
}
