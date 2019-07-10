package bowling.model.frame.state;

import bowling.model.Pins;

public class Hit implements State {

    private final Pins first;

    private Hit(Pins first) {
        this.first = first;
    }

    static State valueOf(Pins first) {
        return new Hit(first);
    }

    @Override
    public State bowl(Pins second) {
        Pins totalPins = first.sum(second);
        return totalPins.equals(Pins.DOWN_ALL) ? Spare.valueOf(first) : Miss.valueOf(first, second);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String printResult() {
        return String.format("   %s   ", first);
    }
}
