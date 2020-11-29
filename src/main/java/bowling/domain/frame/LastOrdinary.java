package bowling.domain.frame;

import bowling.domain.score.Score;

public class LastOrdinary implements LastState {
    private final int score;

    public LastOrdinary(int score) {
        this.score = score;
    }

    @Override
    public Score getScore() {
        return Score.ordinary(score);
    }

}
