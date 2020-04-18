package bowling.refactor.framestate.common;

import bowling.refactor.framestate.State;

public class FirstBowl implements State {

    private final int countOfPin;

    private FirstBowl(final int countOfPin) {
        this.countOfPin = countOfPin;
    }

    public static FirstBowl newInstance(final int countOfPin) {
        return new FirstBowl(countOfPin);
    }

    @Override
    public State Bowl(final int countOfPin) {
        if(this.countOfPin + countOfPin == 10) {
            return Spare.newInstance(this.countOfPin, countOfPin);
        }

        return Miss.newInstance(this.countOfPin, countOfPin);
    }
}
