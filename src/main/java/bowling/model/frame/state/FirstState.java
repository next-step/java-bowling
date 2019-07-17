package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;

import static java.lang.Boolean.FALSE;

public abstract class FirstState implements State {

    private final Pin firstBowl;

    FirstState(Pin firstBowl) {
        this.firstBowl = firstBowl;
    }

    static State of(Pin pin) {
        if (Gutter.isMatch(pin)) {
            return Gutter.getInstance();
        }
        if (Strike.isMatch(pin)) {
            return Strike.getInstance();
        }
        return Hit.valueOf(pin);
    }

    Pin getFirstBowl() {
        return firstBowl;
    }

    @Override
    public State bowl(Pin secondBowl) {
        return SecondState.of(firstBowl, secondBowl);
    }

    @Override
    public Score getScore() {
        return Score.parse(firstBowl);
    }

    @Override
    public Score calculate(Score prevScore) {
        return prevScore.calculate(getScore());
    }

    @Override
    public boolean isFinished() {
        return FALSE;
    }
}