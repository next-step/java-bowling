package bowling.refactor;

import bowling.refactor.framestate.State;
import bowling.refactor.framestate.common.Ready;

public class BowlingFrame {

    private State state;
    private BowlingFrame nextFrame;

    private BowlingFrame() {
        this.state = Ready.newInstance();
        this.nextFrame = null;
    }

    public Score getScore() {
        FrameScore frameScore = state.createFrameScore();
        if (frameScore.canCalculateScore()) {
            return frameScore.getScore();
        }

        return nextFrame.addingUpScore(frameScore);
    }


    private Score addingUpScore(final FrameScore beforeScore) {
        FrameScore addingUpFrameScore = state.addingUpFrameScore(beforeScore);

        if(addingUpFrameScore.canCalculateScore()) {
            return addingUpFrameScore.getScore();
        }

        return nextFrame.addingUpScore(addingUpFrameScore);
    }

}
