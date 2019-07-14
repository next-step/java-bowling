package bowling.model.frame.state;

import bowling.model.Pins;

public class Score {

    private static final int DEFAULT_SCORE = -1;
    public static final Score DEFAULT = Score.of(DEFAULT_SCORE);

    private int count;
    private int score;

    private Score(int count, int score) {
        if(count < 0 ){
            throw new IllegalArgumentException();
        }

        this.count = count;
        this.score = score;
    }

    public static Score of(Pins pins) {
        return new Score(0, pins.count());
    }

    public static Score of(int score) {
        return of(0, score);
    }

    public static Score of(int count, Pins pins) {
        return new Score(count, pins.count());
    }

    public static Score of(int count, int score) {
        return new Score(count, score);
    }

    public static Score of(int count, Pins first, Pins second) {
        return new Score(count, first.sum(second).count());
    }


    Score calculate(Score score) {
        if (score.isCompleted()) {
            return score;
        }
        return new Score(score.getCount() - 1, this.score + score.score);
    }

    Score calculate(int c) {
        if (isCompleted()) {
            return this;
        }
        return new Score(count - 1, this.score + c);
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
