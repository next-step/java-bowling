package bowling.domain.state;

import bowling.domain.value.Score;

public class Strike extends FinishState {
    private static final String STRIKE = "X";

    private Strike() {
    }

    public static State of() {
        return new Strike();
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
