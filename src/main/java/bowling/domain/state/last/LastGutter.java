package bowling.domain.state.last;

import bowling.domain.score.Score;

public class LastGutter implements LastState {
    @Override
    public Score getScore() {
        return Score.gutter();
    }
}
