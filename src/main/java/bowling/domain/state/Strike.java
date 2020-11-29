package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastStrike;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class Strike implements State {
    private final int leftTry;

    public Strike(int leftTry) {
        this.leftTry = leftTry;
    }

    @Override
    public State record(int score) {
        if (leftTry == MIN_LEFT_TRY) {
            return recordLast(score);
        }
        if (score == MAX_SCORE) {
            return new Strike(leftTry - 1);
        }
        if (score == MIN_SCORE) {
            return new Gutter(leftTry - 1);
        }
        return new Ordinary(score, leftTry - 1);
    }

    private State recordLast(int score) {
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
        return Score.strike();
    }
}
