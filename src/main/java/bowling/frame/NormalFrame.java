package bowling.frame;

import bowling.score.Scores;

public class NormalFrame extends Frame {

    public NormalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    @Override
    public boolean pitching() {
        return false;
    }

    public static NormalFrame create(int frameNumber, Scores scores) {
        return new NormalFrame(frameNumber, scores);
    }

    public static NormalFrame create(int frameNumber) {
        return new NormalFrame(frameNumber);
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.FIRST_FRAME_NUMBER);
    }

    @Override
    public Frame next() {
        int nextFrameNumber = getFrameNumber() + 1;
        if (nextFrameNumber < Frame.FINAL_FRAME_NUMBER) {
            return NormalFrame.create(nextFrameNumber, null);
        }
        return FinalFrame.create(nextFrameNumber);
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
