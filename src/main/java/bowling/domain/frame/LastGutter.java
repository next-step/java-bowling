package bowling.domain.frame;

import bowling.domain.score.Score;

public class LastGutter implements LastState {
    @Override
    public Score getScore() {
        return Score.gutter();
    }
}
