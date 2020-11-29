package bowling.domain.state.last;

import bowling.domain.state.Gutter;
import bowling.domain.state.Ordinary;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class LastFrameReady implements State {
    private static final int LEFT_TRY = 2;

    @Override
    public State record(int pins) {
        if (pins == MAX_SCORE) {
            return new Strike(LEFT_TRY);
        }
        if (pins == MIN_SCORE) {
            return new Gutter(LEFT_TRY);
        }
        return new Ordinary(pins, LEFT_TRY);
    }
}
