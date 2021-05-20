package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Score {

    private static final int MINIMUM_SINGLE_SCORE = 0;
    private static final int MAXIMUM_SINGLE_SCORE = 10;

    private static final int INIT_SCORE = 0;
    private static final int INIT_BONUS = 0;

    private int score;
    private int bonus;

    public Score(){
        score = INIT_SCORE;
        bonus = INIT_BONUS;
    }

    public void addScore(int score){
        if (!validScore(score)) {
            throw new CustomException(ErrorCode.INVALID_SCORE);
        }
        this.score += score;
    }

    public int score(){
        return score;
    }

    public boolean hasBonus(){
        return bonus>0;
    }

    private boolean validScore(int score) {
        return score >= MINIMUM_SINGLE_SCORE && score <= MAXIMUM_SINGLE_SCORE;
    }

}
