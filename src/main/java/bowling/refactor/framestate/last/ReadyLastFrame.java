package bowling.refactor.framestate.last;

import bowling.refactor.FrameScore;
import bowling.refactor.framestate.State;

public class ReadyLastFrame implements State {

    public ReadyLastFrame() {
    }

    public static ReadyLastFrame newInstance() {
        return new ReadyLastFrame();
    }

    @Override
    public State Bowl(final int countOfPin) {
        if (countOfPin == 10) {
            return StrikeLastFrame.newInstance();
        }

        return FirstBowlLastFrame.newInstance(countOfPin);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createReady();
    }
}
