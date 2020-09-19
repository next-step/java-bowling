package bowling.frame;

import bowling.score.Scores;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public FinalFrame(int frameNumber) {
        super(frameNumber);
    }

    public static FinalFrame create(int frameNumber) {
        return new FinalFrame(frameNumber, Scores.newInstance());
    }

    public static FinalFrame create() {
        return new FinalFrame(10);
    }

    @Override
    public Frame next() {
        return FinalFrame.create(getFrameNumber() + 1);
    }

    @Override
    public boolean isFinal() {
        return getFrameNumber() >= 10;
    }
}
