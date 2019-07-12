package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;
import bowling.utils.Pretty;

import static bowling.model.Pins.DOWN_ALL;

public class Miss extends SecondState {

    private static final String ERROR_MESSAGE = "핀을 모두 쓰러트릴 수 없습니다";

    private Miss(Pins firstBowl, Pins secondBowl) {
        super(firstBowl, secondBowl);
    }

    static State valueOf(Pins firstBowl, Pins secondBowl) {
        if (DOWN_ALL.equals(firstBowl) || DOWN_ALL.equals(secondBowl)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return new Miss(firstBowl, secondBowl);
    }

    @Override
    public String printResult() {
        return Pretty.putPartitionOfState(getFirstBowl().toString(), getSecondBowl().toString());
    }
}
