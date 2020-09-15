package bowling.domain;

import bowling.score.Score;
import bowling.score.Scores;

public class NormalFrame extends Frame {
    private Scores scores;

    private NormalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    private static NormalFrame of(int frameNumber, Scores scores) {
        return new NormalFrame(frameNumber, scores);
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.FIRST_FRAME, Scores.init());
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;
        return nextFrameNumber < Frame.LAST_FRAME
                ? NormalFrame.of(nextFrameNumber, Scores.init())
                : FinalFrame.of(nextFrameNumber, Scores.init());
    }

    @Override
    public void bowl(Score score) {

    }

    public boolean canBowl() {
        if (scores == null) {
            return true;
        }

        if (scores.isStrike()) {
            return false;
        }

        return !scores.isDone();
    }

}
