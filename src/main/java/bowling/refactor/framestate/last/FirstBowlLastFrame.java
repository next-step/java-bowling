package bowling.refactor.framestate.last;

import bowling.refactor.framestate.State;
import bowling.refactor.framestate.common.Miss;
import bowling.refactor.framestate.common.Spare;

public class FirstBowlLastFrame implements State {
    private final int countOfPin;

    private FirstBowlLastFrame(final int countOfPin) {
        this.countOfPin = countOfPin;
    }

    public static FirstBowlLastFrame newInstance(final int countOfPin) {
        return new FirstBowlLastFrame(countOfPin);
    }

    @Override
    public State Bowl(final int countOfPin) {
        if(this.countOfPin + countOfPin == 10) {
            return SpareLastFrame.newInstance(this.countOfPin, countOfPin);
        }

        return Miss.newInstance(this.countOfPin, countOfPin);
    }
}
