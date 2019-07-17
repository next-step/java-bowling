package bowling.model.frame.state;

import bowling.model.Pin;

import static bowling.model.Pin.MAX;

// todo : count 추출
public class Score {

    public static final int DEFAULT_SCORE = -1;
    public static final int ZERO_OF_COUNT = 0;
    private static final int ONCE_OF_COUNT = 1;
    private static final int TWICE_OF_COUNT = 2;
    static final Score STRIKE = Score.of(TWICE_OF_COUNT, MAX);
    static final Score SPARE = Score.of(ONCE_OF_COUNT, MAX);
    public static final Score DEFAULT = Score.of(ZERO_OF_COUNT, DEFAULT_SCORE);

    private int count;
    private int score;

    private Score(int count, int score) {
        this.count = count;
        this.score = score;
    }

    public static Score parse(Pin pin) {
        return of(ZERO_OF_COUNT, pin.count());
    }

    static Score of(int score) {
        return of(ZERO_OF_COUNT, score);
    }

    static Score of(int count, int score) {
        return new Score(count, score);
    }

    Score calculate(Pin pin) {
        return calculate(parse(pin));
    }

    Score calculate(Score score) {
        return new Score(this.count - 1, this.score + score.score);
    }

    public boolean isCompleted() {
        return ZERO_OF_COUNT == count;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "count=" + count +
                ", score=" + score +
                '}';
    }
}
