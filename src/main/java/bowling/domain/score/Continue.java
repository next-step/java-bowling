package bowling.domain.score;

import bowling.domain.pin.Pins;

public class Continue implements State{

    private final Pins first;

    private Continue(Pins first) {
        this.first = first;
    }

    public static State of(Pins pins) {
        return new Continue(pins);
    }

    @Override
    public State roll(Pins pins) {
       return null;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Score calculate(Score baseScore) {
        return null;
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public boolean canRoll(State state) {
        return false;
    }
}
