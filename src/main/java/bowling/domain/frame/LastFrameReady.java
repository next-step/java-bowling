package bowling.domain.frame;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class LastFrameReady implements State {
    private static final int LEFT_TRY = 2;

    @Override
    public State record(int score) {
        if (score == MAX_SCORE) {
            return new Strike(LEFT_TRY);
        }
        if (score == MIN_SCORE) {
            return new Gutter(LEFT_TRY);
        }
        return new Ordinary(score, LEFT_TRY);
    }
}
