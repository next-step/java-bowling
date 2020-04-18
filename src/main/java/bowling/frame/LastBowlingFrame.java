package bowling.frame;

import bowling.FrameScore;
import bowling.Score;
import bowling.framestate.State;
import bowling.framestate.last.ReadyLastFrame;

import java.util.Arrays;

public class LastBowlingFrame implements BowlingFrame {

    private State state;

    private LastBowlingFrame(final State state) {
        this.state = state;
    }

    public static LastBowlingFrame newInstance() {
        return new LastBowlingFrame(ReadyLastFrame.newInstance());
    }

    @Override
    public void bowl(final int countOfPin) {
        state.bowl(countOfPin);
    }

    @Override
    public Score getScore() {
        FrameScore frameScore = state.createFrameScore();
        if (frameScore.canCalculateScore()) {
            return frameScore.getScore();
        }

        return addingUpScore(frameScore);
    }

    @Override
    public Score addingUpScore(final FrameScore beforeScore) {
        FrameScore addingUpFrameScore = beforeScore.addingUp(Arrays.asList(0, 0, 0));
        return addingUpFrameScore.getScore();
    }

    @Override
    public boolean isOver() {
        return state.isOver();
    }

    @Override
    public BowlingFrame addNextFrame(final int frameNumber) {
        throw new IllegalStateException("It is last frame. can not add next frame");
    }

    @Override
    public State getState() {
        return state;
    }
}
