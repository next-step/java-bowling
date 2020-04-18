package bowling.refactor.framestate.last;

import bowling.refactor.framestate.State;

public class StrikeLastFrameOver implements State {

    private int secondPin;

    private StrikeLastFrameOver(final int countOfPin) {
        this.secondPin = countOfPin;
    }

    public static State newInstance(final int countOfPin) {
        return new StrikeLastFrameOver(countOfPin);
    }

    @Override
    public State Bowl(int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }
}
