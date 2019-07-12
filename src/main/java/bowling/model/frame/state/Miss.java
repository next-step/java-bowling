package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.Pins.DOWN_ALL;

public class Miss extends SecondState {

    private Miss(Pins firstBowl, Pins secondBowl) {
        super(firstBowl, secondBowl);
    }

    static State valueOf(Pins firstBowl, Pins secondBowl) {
        if (DOWN_ALL.equals(firstBowl) || DOWN_ALL.equals(secondBowl)) {
            throw new InvalidMissException();
        }
        return new Miss(firstBowl, secondBowl);
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(getFirstBowl().toString(), getSecondBowl().toString());
    }
}
