package bowling.domain.frame;

import bowling.domain.score.FinalScores;
import bowling.domain.score.NormalScores;
import bowling.domain.score.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frameNumber, Scores scores) {
        super(frameNumber, scores);
    }

    public static NormalFrame of(int frameNumber, Scores scores) {
        return new NormalFrame(frameNumber, scores);
    }

    public static NormalFrame first() {
        return new NormalFrame(Frame.FIRST_FRAME, NormalScores.init());
    }

    @Override
    public Frame next() {
        int nextFrameNumber = this.frameNumber + 1;
        Frame nextFrame = nextFrameNumber < Frame.LAST_FRAME
                ? NormalFrame.of(nextFrameNumber, NormalScores.init())
                : FinalFrame.of(nextFrameNumber, FinalScores.init());

        addNext(nextFrame);
        return nextFrame;
    }

    private void addNext(Frame nextFrame) {
        this.next = nextFrame;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
