package bowling.domain.state;

import bowling.domain.frame.InvalidFrameRecordActionException;
import bowling.domain.score.Score;
import bowling.domain.state.last.LastGutter;
import bowling.domain.state.last.LastOrdinary;
import bowling.domain.state.last.LastSpare;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;
import static bowling.domain.score.Score.ordinary;

public class Ordinary implements State {
    private final int score;
    private int leftTry = MIN_LEFT_TRY;

    public Ordinary(int score) {
        this.score = score;
    }

    public Ordinary(int score, int leftTry) {
        this.score = score;
        this.leftTry = leftTry;
    }

    @Override
    public State record(int pins) {
        if (pins == MAX_SCORE) {
            throw new InvalidFrameRecordActionException();
        }
        if (this.score + pins == MAX_SCORE) {
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
        return ordinary(score);
    }
}
