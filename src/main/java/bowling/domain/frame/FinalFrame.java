package bowling.domain.frame;

import bowling.domain.score.FinalScores;
import bowling.domain.score.Scores;

public class FinalFrame extends Frame {

    private FinalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static FinalFrame of(int frameNumber, Scores scores) {
        return new FinalFrame(frameNumber, scores);
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;

        return FinalFrame.of(nextFrameNumber, FinalScores.init());
    }

    @Override
    public int getScore() {
        return 0;
    }

}
