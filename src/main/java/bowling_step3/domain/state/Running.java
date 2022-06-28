package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

abstract class Running implements Status {
    public boolean isFinished() {
        return false;
    }

    public Scores getScore() {
        throw new UnsupportedOperationException("Cannot get score, it's still running.");
    }
}