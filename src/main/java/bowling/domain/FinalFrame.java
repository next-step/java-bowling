package bowling.domain;

import bowling.score.FinalScores;
import bowling.score.Scores;

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

}
