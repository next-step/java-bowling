package bowling.state.domain;

import bowling.frame.domain.Score;
import bowling.pin.domain.Pin;

public class Ready extends Running {

    public static Ready of() {
        return new Ready();
    }

    @Override
    public State roll(Pin felled) {
        if (felled.isAllFelled()) {
            return Strike.of();
        }
        return Cover.of(felled);
    }

    @Override
    public Score getScore() {
        return Score.ZERO;
    }

    @Override
    public String view() {
        return "";
    }

}
