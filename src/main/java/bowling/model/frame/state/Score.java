package bowling.model.frame.state;

import bowling.model.Pins;

public class Score {

    private static final int DEFAULT_SCORE = -1;
    private static final int DEFAULT_COUNT = 0;
    public static final Score DEFAULT = Score.of(DEFAULT_SCORE);

    private int count;
    private int score;

    private Score(int count, int score) {
        this.count = count;
        this.score = score;
    }

    static Score of(Pins pins) {
        return of(pins.count());
    }

    static Score of(int score) {
        return of(DEFAULT_COUNT, score);
    }

    static Score of(int count, int score) {
        return new Score(count, score);
    }

    Score calculate(Pins pins) {
        return calculate(of(pins));
    }

    Score calculate(Score score) {
        if (isCompleted()) {
            return this;
        }
        return new Score(count- 1, this.score + score.score);
    }

    public int getScore() {
        return score;
    }

    int getCount() {
        return count;
    }

    public boolean isCompleted() {
        return count == 0;
    }

    @Override
    public String toString() {
        return "Score{" +
                "count=" + count +
                ", score=" + score +
                '}';
    }
}
