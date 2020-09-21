package bowling.frame;

import bowling.score.NormalScores;
import bowling.score.Scores;

public class NormalFrame extends Frame {

    public NormalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static NormalFrame create(int frameNumber) {
        return new NormalFrame(frameNumber, NormalScores.newInstance());
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.FIRST_FRAME_NUMBER, NormalScores.newInstance());
    }

    @Override
    public Frame next() {
        int nextFrameNumber = getFrameNumber() + INCREASE_FRAME_NUMBER;
        if (nextFrameNumber < Frame.FINAL_FRAME_NUMBER) {
            return NormalFrame.create(nextFrameNumber);
        }
        return FinalFrame.create(nextFrameNumber);
    }

}
