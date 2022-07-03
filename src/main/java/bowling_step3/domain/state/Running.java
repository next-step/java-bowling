package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public abstract class Running implements Status {
    private Scores scores;

    public Running(Scores scores) {
        this.scores = scores;
    }

    public boolean isFinished() {
        return false;
    }

    public Integer getScore() {
        throw new UnsupportedOperationException("Cannot get score, it's still running.");
    }

    public int calculateAdditionalScore(Status status) {
        throw new UnsupportedOperationException("Cannot calculate, it's still running.");
    }

    public Scores scores() {
        return this.scores;
    }

    public Status pitch(int numPins) {
        Scores scores = this.scores().pitch(numPins);
        if (scores.isStrike()) {
            return new Strike();
        }
        return new FirstPitch(scores);
    }
}