package bowling.domain.frame;

import bowling.domain.score.Score;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class Spare implements State {
    private final int score;

    public Spare(int score) {
        this.score = score;
    }

    @Override
    public State record(int score) {
        if (score == MAX_SCORE) {
            return new LastStrike();
        }
        if (score == MIN_SCORE) {
            return new LastGutter();
        }
        return new LastOrdinary(score);
    }

    @Override
    public Score getScore() {
        return Score.spare(score);
    }
}
