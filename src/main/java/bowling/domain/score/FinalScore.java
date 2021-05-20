package bowling.domain.score;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class FinalScore implements Score {
    private int score;
    private boolean endedScoring;

    public FinalScore() {
        score = INIT_SCORE;
        endedScoring = INIT_ENDED_SCORING;
    }

    @Override
    public void addScore(int score) {
        if (!validScore(score)) {
            throw new CustomException(ErrorCode.INVALID_SCORE);
        }
        if (endedScoring) {
            throw new CustomException(ErrorCode.INVALID_SCORE_ADDITION);
        }
        this.score += score;
    }

    @Override
    public void endScoring() {
        this.endedScoring = ENDED_SCORING;
    }

    @Override
    public boolean endedScoring() {
        return endedScoring;
    }

    @Override
    public int score() {
        return score;
    }

    private boolean validScore(int score) {
        return score >= MINIMUM_SINGLE_SCORE && score <= MAXIMUM_SINGLE_SCORE;
    }

}
