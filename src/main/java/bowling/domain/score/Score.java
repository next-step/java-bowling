package bowling.domain.score;

import bowling.domain.score.exception.DoNotCalculateException;

import java.util.concurrent.atomic.AtomicInteger;

public class Score {
    private final int left;
    private int score;

    public Score(int left, int score) {
        this.left = left;
        this.score = score;
    }

    public Score(int left, Score before) {
        this.left = left;
        this.score = before.score;
    }

    public Score nextScore(int pins) {
        return new Score(new AtomicInteger(this.left).decrementAndGet(), this.score += pins);
    }

    public int getScore() {
        if (!doNotCalculate()) {
            throw new DoNotCalculateException(this.left);
        }

        return this.score;
    }

    public boolean doNotCalculate() {
        return this.left <= 0;
    }

    public int left() {
        return left;
    }
}
