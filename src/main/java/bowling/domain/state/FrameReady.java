package bowling.domain.state;

import bowling.domain.state.last.LastStrike;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class FrameReady implements State {
    @Override
    public State record(int pins) {
        if (pins == MAX_SCORE) {
            return new LastStrike();
        }
        if (pins == MIN_SCORE) {
            return new Gutter();
        }
        return new Ordinary(pins);
    }
}
