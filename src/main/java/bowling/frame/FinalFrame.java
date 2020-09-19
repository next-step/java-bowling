package bowling.frame;

import bowling.score.Scores;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static FinalFrame create(int frameNumber) {
        return new FinalFrame(frameNumber, Scores.newInstance());
    }

    @Override
    public Frame next() {
        return FinalFrame.create(getFrameNumber() + INCREASE_FRAME_NUMBER);
    }

    @Override
    public boolean isFinal() {
        return getFrameNumber() >= Frame.FINAL_FRAME_NUMBER;
    }
}
