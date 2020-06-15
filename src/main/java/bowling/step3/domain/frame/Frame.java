package bowling.step3.domain.frame;

import bowling.step3.domain.scores.Scores;

public class Frame {

    protected final int frame;
    protected Scores scores;

    protected Frame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }

    public int getValue() {
        return frame;
    }

    public void createNextFrame (Scores scores) {}

    public Frame getNextFrame () {
        return null;
    }
}