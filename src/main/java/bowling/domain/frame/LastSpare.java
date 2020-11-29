package bowling.domain.frame;

import bowling.domain.score.Score;

public class LastSpare implements LastState {
    private final int score;

    public LastSpare(int score) {
        this.score = score;
    }

    @Override
    public Score getScore() {
        return Score.spare(score);
    }

}
