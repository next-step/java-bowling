package bowling.domain.state.last;

import bowling.domain.score.Score;

public class LastOrdinary implements LastState {
    private final int pins;

    public LastOrdinary(int pins) {
        this.pins = pins;
    }

    @Override
    public Score getScore() {
        return Score.ordinary(pins);
    }

}
