package bowling.domain;

import bowling.score.FinalScores;
import bowling.score.NormalScores;
import bowling.score.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    private static NormalFrame of(int frameNumber, Scores scores) {
        return new NormalFrame(frameNumber, scores);
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.FIRST_FRAME, NormalScores.init());
    }

    @Override
    public boolean canBowl() {
        if (scores == null) {
            return true;
        }

        if (scores.isStrike()) {
            return false;
        }

        return !scores.isDone();
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;
        return nextFrameNumber < Frame.LAST_FRAME
                ? NormalFrame.of(nextFrameNumber, NormalScores.init())
                : FinalFrame.of(nextFrameNumber, FinalScores.init());
    }
}
