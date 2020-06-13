package bowling.step2.domain.frame;

import bowling.step2.domain.Score;
import bowling.step2.domain.scores.Scores;

public class FinalFrame implements Frame {

    private final int frame;
    private final Scores scores;
    private final Frame prevFrame;

    private FinalFrame(int frame, Scores scores, Frame prevFrame) {
        this.frame = frame;
        this.scores = scores;
        this.prevFrame = prevFrame;
    }

    public static Frame of (int frame, Scores scores, Frame prevFrame) {
        return new FinalFrame(frame, scores, prevFrame);
    }

    @Override
    public Scores getScores () {
        return scores;
    }

    @Override
    public Frame createNextFrame () {
        return null;
    }

    @Override
    public Frame getPrevFrame () {
        return prevFrame;
    }

    @Override
    public int getValue() {
        return frame;
    }
}