package bowling.domain.frame;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class FrameReady implements State {
    @Override
    public State record(int score) {
        if (score == MAX_SCORE) {
            return new LastStrike();
        }
        if (score == MIN_SCORE) {
            return new Gutter();
        }
        return new Ordinary(score);
    }
}
