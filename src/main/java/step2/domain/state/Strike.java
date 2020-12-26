package step2.domain.state;

import step2.domain.Score;

public class Strike extends Finished {

    private static final int MAX_SCORE = 10;
    private static final int ZERO_CHANCE = 0;
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public Score getScore() {
        return Score.of(MAX_SCORE, ZERO_CHANCE);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        if(score.validateChance()) {
            return score;
        }
        return score.bowl(MAX_SCORE);
    }

    @Override
    public String toString() {
        return STRIKE_SYMBOL;
    }
}
