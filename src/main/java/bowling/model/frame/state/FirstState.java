package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ALL;

public abstract class FirstState implements State {

    final Pins firstBowl;

    FirstState(Pins firstBowl) {
        this.firstBowl = firstBowl;
    }

    static State of(Pins pins) {
        if(Gutter.isMatch(pins)){
            return Gutter.getInstance();
        }
        if(Strike.isMatch(pins)){
            return Strike.getInstance();
        }
        return Hit.valueOf(pins);
    }

    @Override
    public State bowl(Pins secondBowl) {
        if (firstBowl.sum(secondBowl).equals(DOWN_ALL)) {
            return Spare.valueOf(firstBowl);
        }
        return Miss.valueOf(firstBowl, secondBowl);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
