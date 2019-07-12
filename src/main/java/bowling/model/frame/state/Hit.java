package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ALL;

public class Hit implements State {

    private final Pins firstBowl;

    private Hit(Pins firstBowl) {
        this.firstBowl = firstBowl;
    }

    static State valueOf(Pins first) {
        return new Hit(first);
    }

    @Override
    public State bowl(Pins secondBowl) {
        Pins totalDownPins = firstBowl.sum(secondBowl);
        if (totalDownPins.equals(DOWN_ALL)) {
            return Spare.valueOf(firstBowl);
        }
        return Miss.valueOf(firstBowl, secondBowl);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String printResult() {
        return firstBowl.toString();
    }
}
