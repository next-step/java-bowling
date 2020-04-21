package bowling.frame;

import bowling.FrameScore;
import bowling.Pin;
import bowling.Score;
import bowling.dto.FrameState;
import bowling.framestate.Ready;
import bowling.framestate.State;

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
    public void bowl(final Pin pinCount) {
        state = state.bowl(pinCount);
    }

    @Override
    public Score getTotalScore(final Score beforeScore) {
        return getFrameScore().add(beforeScore);
    }

    @Override
    public Score getFrameScore() {
        return getScore(state.createFrameScore());
    }

    @Override
    public Score sumBeforeScore(final FrameScore beforeScore) {
        return getScore(state.sumBeforeScore(beforeScore));
    }

    private Score getScore(final FrameScore beforeScore) {
        if (beforeScore.canCalculateSelfScore()) {
            return beforeScore.getScore();
        }

        if (Objects.isNull(nextFrame)) {
            return getNowScore(beforeScore);
        }

        return nextFrame.sumBeforeScore(beforeScore);
    }

    private Score getNowScore(final FrameScore beforeScore) {
        FrameScore nowFrameScore = FrameScore.newInstanceWithNoLeftCount(beforeScore);
        return nowFrameScore.getScore();
    }

    @Override
    public BowlingFrame appendNextFrame(final int frameNumber) {
        if (!Objects.isNull(nextFrame)) {
            throw new IllegalStateException("can not add next frame. It already existed");
        }

        nextFrame = BowlingFrame.newInstance(frameNumber + 1);
        return nextFrame;
    }

    @Override
    public boolean isOver() {
        return state.isOver();
    }

    @Override
    public boolean canCalculateScore() {
        return canGetCalculateScore(state.createFrameScore());
    }

    @Override
    public boolean canCalculateWithBeforeScore(final FrameScore frameScore) {
        return canGetCalculateScore(state.sumBeforeScore(frameScore));
    }

    private boolean canGetCalculateScore(FrameScore frameScore) {
        if (frameScore.canCalculateSelfScore()) {
            return true;
        }

        if (Objects.isNull(nextFrame)) {
            return false;
        }

        return nextFrame.canCalculateWithBeforeScore(frameScore);
    }

    @Override
    public FrameState makeFrameState() {
        return FrameState.newInstance(state, null);
    }

}
