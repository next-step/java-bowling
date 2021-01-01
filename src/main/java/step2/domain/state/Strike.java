package step2.domain.state;

import step2.domain.Pitch;
import step2.domain.Score;

public class Strike extends Finished {

    public final static String SYMBOL = "X";

    @Override
    public Score getScore() {
        return Score.of(Pitch.MAX_SCORE, 2);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(Pitch.MAX_SCORE);
    }

    @Override
    public String toString() {
        return SYMBOL;
    }

}
