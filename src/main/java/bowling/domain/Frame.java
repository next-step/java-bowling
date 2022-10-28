package bowling.domain;

public class Frame {
    private final Scores scores;

    public Frame() {
        this.scores = new Scores();
    }

    public Scores getScores() {
        return scores;
    }

    void record(int downPinCount) {
        scores.record(downPinCount);
    }

    public boolean isEndFrame() {
        if (scores.isStrike()) {
            return true;
        }
        if (scores.tryOver()) {
            return true;
        }
        return false;
    }

}