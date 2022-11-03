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
        if (scores.isTryOver()) {
            return true;
        }
        return false;
    }

    public int getPinScore() {
        return scores.getPinScore();
    }

    public FrameScore getResult() {
        return FrameScore.from(scores);
    }

    public int getFirstPitchScore() {
        return scores.getFirstScore();
    }

    public int getSecondPitchScore() {
        return scores.getSecondPitchScore();
    }

    public int getTryCount() {
        return scores.getTryCount();
    }

    public boolean isStrike() {
        return scores.isStrike();
    }

    public boolean isSpare() {
        return scores.isSpare();
    }

}