package bowling.domain.state.last;

import bowling.domain.score.Score;

public class LastStrike implements LastState {

    @Override
    public Score getScore() {
        return Score.strike();
    }
}
