package bowling.step3.domain.frame;

import bowling.step3.domain.*;
import bowling.step3.domain.scores.*;

public class NormalFrame extends Frame {

    private Frame nextFrame;

    private NormalFrame(int frame, Scores scores, Frame nextFrame) {
        super(frame, scores);
        this.nextFrame = nextFrame;
    }

    public static Frame of(int frame, Scores scores, Frame nextFrame) {
        return new NormalFrame(frame, scores, nextFrame);
    }

    public void createNextFrame(Scores scores) {
        this.scores = scores;
        if (!scores.isType(ScoreType.STRIKE) && !scores.isFullOf()) {
            return;
        }
        int nextFrameValue = frame + 1;
        this.nextFrame = nextFrameValue < Frames.LAST_FRAME
                            ? of(nextFrameValue, NormalScores.init(), null)
                            : FinalFrame.of(nextFrameValue, FinalScores.init());
    }

    public Frame getNextFrame () {
        return nextFrame;
    }
}