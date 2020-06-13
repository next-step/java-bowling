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
    public Frame createNextFrame () {
        int nextFrameValue = frame + 1;
        if (nextFrameValue < Frames.LAST_FRAME) {
            return of(nextFrameValue, NormalScores.init(), this);
        }
        return FinalFrame.of(nextFrameValue, FinalScores.init(), this);
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