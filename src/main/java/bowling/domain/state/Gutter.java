package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastSpare;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public class Gutter implements State {
    private int leftTry = MIN_LEFT_TRY;

    public Gutter() {
    }

    public Gutter(int leftTry) {
        this.leftTry = leftTry;
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_SCORE) {
            return recordSpare(pins);
        }
        if (pins == MIN_SCORE) {
            return new LastGutter();
        }
        return new LastOrdinary(pins);
    }

    private State recordSpare(int score) {
        if (leftTry == MIN_LEFT_TRY) {
            return new LastSpare(score);
        }
        return new Spare(score);
    }


    @Override
    public Score getScore() {
        return Score.gutter();
    }
}
