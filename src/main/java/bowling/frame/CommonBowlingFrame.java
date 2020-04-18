package bowling.frame;

import bowling.FrameScore;
import bowling.Score;
import bowling.framestate.State;
import bowling.framestate.common.Ready;

import java.util.Arrays;
import java.util.Objects;

public class CommonBowlingFrame implements BowlingFrame {

    private State state;
    private BowlingFrame nextFrame;

    private CommonBowlingFrame(final State state, final BowlingFrame nextFrame) {
        this.state = state;
        this.nextFrame = nextFrame;
    }

    public static CommonBowlingFrame newInstance() {
        return new CommonBowlingFrame(Ready.newInstance(), null);
    }

    @Override
    public void bowl(final int countOfPin) {
        state.bowl(countOfPin);
    }

    @Override
    public Score getScore() {
        FrameScore frameScore = state.createFrameScore();

        return getScore(frameScore);
    }

    @Override
    public Score addingUpScore(final FrameScore beforeScore) {
        FrameScore addingUpFrameScore = state.addingUpFrameScore(beforeScore);

        return getScore(addingUpFrameScore);
    }

    private Score getScore(final FrameScore frameScore) {
        if (frameScore.canCalculateScore()) {
            return frameScore.getScore();
        }

        if (Objects.isNull(nextFrame)) {
            return getNowScore(frameScore);
        }
        return nextFrame.addingUpScore(frameScore);
    }

    private Score getNowScore(final FrameScore frameScore) {
        FrameScore nowScore = frameScore.addingUp(Arrays.asList(0, 0));
        return nowScore.getScore();
    }


    @Override
    public boolean isOver() {
        return state.isOver();
    }

    @Override
    public BowlingFrame addNextFrame(final int frameNumber) {
        if(!Objects.isNull(nextFrame)) {
            throw new IllegalStateException("can not add next frame. It already existed");
        }

        nextFrame = BowlingFrame.newInstance(frameNumber + 1);
        return nextFrame;
    }

    @Override
    public State getState() {
        return state;
    }

}
