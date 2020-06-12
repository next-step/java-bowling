package bowling.step2.domain.frame;

import bowling.step2.domain.scores.Scores;

public class FinalFrame implements Frame {

    private final int frame;
    private final Scores scores;

    private FinalFrame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public static Frame of (int frame, Scores scores) {
        return new FinalFrame(frame, scores);
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
    public Frame getNextFrame () {
        return null;
    }

    @Override
    public boolean hasNext () {
        return false;
    }

    @Override
    public int getValue() {
        return frame;
    }
}