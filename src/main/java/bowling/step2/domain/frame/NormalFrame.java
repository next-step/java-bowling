package bowling.step2.domain.frame;

import bowling.step2.domain.Frames;
import bowling.step2.domain.Score;
import bowling.step2.domain.scores.FinalScores;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;

public class NormalFrame implements Frame {

    private final int frame;
    private final Scores scores;
    private final Frame prevFrame;

    protected NormalFrame(int frame, Scores scores, Frame prevFrame) {
        this.frame = frame;
        this.scores = scores;
        this.prevFrame = prevFrame;
    }

    public static NormalFrame of (int frame, Scores scores, Frame prevFrame) {
        return new NormalFrame(frame, scores, prevFrame);
    }


    @Override
    public Scores getScores () {
        return scores;
    }

    @Override
    public Frame createNextFrame (Scores scores) {
        Frame now = of(frame, scores, prevFrame);
        if (!scores.isStrike() && !scores.isFullOf()) {
            return now;
        }
        int nextFrameValue = frame + 1;
        return nextFrameValue < Frames.LAST_FRAME
                ? of(nextFrameValue, NormalScores.init(), now)
                : FinalFrame.of(nextFrameValue, FinalScores.init(), now);
    }

    @Override
    public Frame getPrevFrame () {
        return prevFrame;
    }

    @Override
    public int getValue () {
        return frame;
    }
}