package bowling.frame;

import bowling.score.FinalScores;
import bowling.score.Scores;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static FinalFrame create(int frameNumber) {
        return new FinalFrame(frameNumber, FinalScores.newInstance());
    }

    @Override
    public Frame next() {
        return FinalFrame.create(getFrameNumber() + INCREASE_FRAME_NUMBER);
    }

}
