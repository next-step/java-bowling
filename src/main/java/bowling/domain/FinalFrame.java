package bowling.domain;

import bowling.score.Score;
import bowling.score.Scores;

public class FinalFrame extends Frame {

    public FinalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static FinalFrame of(int frameNumber, Scores scores) {
        return new FinalFrame(frameNumber, scores);
    }

    public BowlResult bowl(int value) {
        return null;
    }

    @Override
    public void bowl(Score score) {

    }

    public boolean canBowl() {
        return false;
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;

        return FinalFrame.of(nextFrameNumber, Scores.init());
    }

}
