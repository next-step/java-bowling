package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ALL;

public abstract class SecondState extends FirstState {

    private static final String ERROR_MESSAGE_OF_TRY_BOWLING = "더 이상 볼을 던질 수 없습니다";
    private final Pins secondBowl;

    SecondState(Pins firstBowl, Pins secondBowl) {
        super(firstBowl);
        this.secondBowl = secondBowl;
    }

    static State of(Pins firstBowl, Pins secondBowl) {
        Pins totalPins = firstBowl.sum(secondBowl);

        if (DOWN_ALL.equals(totalPins)) {
            return Spare.valueOf(firstBowl);
        }
        return Miss.valueOf(firstBowl, secondBowl);
    }

    Pins getSecondBowl() {
        return secondBowl;
    }

    @Override
    public State bowl(Pins secondBowl) {
        throw new UnsupportedOperationException(ERROR_MESSAGE_OF_TRY_BOWLING);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
