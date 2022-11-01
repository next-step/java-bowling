package bowling.domain;

public class Frame {
    protected final Scores scores = new Scores();

    public Scores getScores() {
        return scores;
    }

    public void record(int downPinCount) {
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

    public int getScore() {
        return scores.sumOfDownPins();
    }

}