package bowling.domain.state.last;

import bowling.domain.score.Score;

public class LastSpare implements LastState {
    private final int pins;

    public LastSpare(int pins) {
        this.pins = pins;
    }

    @Override
    public Score getScore() {
        return Score.spare(pins);
    }

}
