package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

import static bowling_step3.domain.Scores.MAX_SCORE;

public class Strike extends Done {
    public Strike() {
        super(Scores.strike());
    }

    public int calculateAdditionalScore(Scores scores) {
        if (scores.isStrike()) {
            return MAX_SCORE + scores.sumOfTwo() * 2;
        }
        return MAX_SCORE + scores.sumOfTwo();
    }
}
