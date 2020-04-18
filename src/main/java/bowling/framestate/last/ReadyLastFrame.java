package bowling.framestate.last;

import bowling.FrameScore;
import bowling.framestate.State;

import java.util.Arrays;

public class ReadyLastFrame implements State {

    public ReadyLastFrame() {
    }

    public static ReadyLastFrame newInstance() {
        return new ReadyLastFrame();
    }

    @Override
    public State bowl(final int countOfPin) {
        if (countOfPin == 10) {
            return StrikeLastFrame.newInstance();
        }

        return FirstBowlLastFrame.newInstance(countOfPin);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createReady();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(0, 0));
    }

    @Override
    public boolean isOver() {
        return false;
    }
}
