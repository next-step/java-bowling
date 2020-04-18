package bowling.refactor.framestate.last;

import bowling.refactor.framestate.State;

public class StrikeLastFrame implements State {

    public StrikeLastFrame() {
    }

    public static StrikeLastFrame newInstance() {
        return new StrikeLastFrame();
    }

    @Override
    public State Bowl(final int countOfPin) {
        return StrikeLastFrameOver.newInstance(countOfPin);
    }
}
