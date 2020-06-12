package bowling.step2.domain.frame;

import bowling.step2.domain.Frames;
import bowling.step2.domain.scores.FinalScores;
import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;

public class NormalFrame implements Frame {

    private final int frame;
    private final Scores scores;
    private Frame nextFrame;

    private NormalFrame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public static NormalFrame of (int frame, Scores scores) {
        return new NormalFrame(frame, scores);
    }


    @Override
    public Scores getScores () {
        return scores;
    }

    @Override
    public Frame createNextFrame () {
        int nextFrame = frame + 1;
        if (nextFrame < Frames.LAST_FRAME) {
            this.nextFrame = of(nextFrame, NormalScores.init());
            return this.nextFrame;
        }
        this.nextFrame = FinalFrame.of(nextFrame, FinalScores.init());
        return this.nextFrame;
    }

    @Override
    public Frame getNextFrame () {
        return nextFrame;
    }


    @Override
    public boolean hasNext () {
        return nextFrame != null;
    }

    @Override
    public int getValue () {
        return frame;
    }
}