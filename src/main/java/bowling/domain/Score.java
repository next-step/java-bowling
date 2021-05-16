package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Score {

    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;

    private static final int INIT_SCORE = 0;
    private static final boolean INIT_ENDED_SCORING = false;
    private static final boolean ENDED_SCORING = true;

    private int score;
    private boolean endedScoring;

    public Score() {
        score = INIT_SCORE;
        endedScoring = INIT_ENDED_SCORING;
    }

    public void addScore(int score) {
        if(!valid(score)){
            throw new CustomException(ErrorCode.INVALID_SCORE);
        }
        this.score += score;
    }

    public void endScoring() {
        this.endedScoring = ENDED_SCORING;
    }

    private boolean valid(int score){
        return score>=MINIMUM_SCORE && score<= MAXIMUM_SCORE;
    }
}
