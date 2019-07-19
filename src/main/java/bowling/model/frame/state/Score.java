package bowling.model.frame.state;

import bowling.model.Count;
import bowling.model.DownPin;

import static bowling.model.Count.*;
import static bowling.model.DownPin.MAX;

public class Score {

    public static final int DEFAULT_SCORE = -1;
    public static final Score DEFAULT = Score.of(ZERO, DEFAULT_SCORE);
    static final Score SPARE = Score.of(ONCE, MAX);
    static final Score STRIKE = Score.of(TWICE, MAX);

    private Count count;
    private int score;

    private Score(Count count, int score) {
        this.count = count;
        this.score = score;
    }

    public static Score of(int score) {
        return new Score(COUNT_ZERO, score);
    }

    public static Score of(DownPin downPin) {
        return new Score(COUNT_ZERO, downPin.count());
    }

    static Score of(int count, int score) {
        return new Score(Count.of(count), score);
    }

    Score calculate(Score score) {
        if (isDefaultValue(score) || isCompleted()) {
            return this;
        }

        return new Score(count.decrease(), this.score + score.score);
    }

    public boolean isCompleted() {
        return COUNT_ZERO.isMatch(count);
    }

    public int getScore() {
        return score;
    }

    private boolean isDefaultValue(Score score) {
        return DEFAULT_SCORE == score.score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "count=" + count +
                ", score=" + score +
                '}';
    }
}
