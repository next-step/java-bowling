package bowling.entity;

import java.util.Objects;

public class Score {
    private final int score;
    private final int remain;

    public Score(int score) {
        this.score = score;
        this.remain = 0;
    }

    public Score(int score, int remain) {
        this.score = score;
        this.remain = remain;
    }

    public boolean calculatePossible() {
        return remain == 0;
    }

    public Score calculate(int pin) {
        return new Score(this.score + pin, remain -1);
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && remain == score1.remain;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remain);
    }
}
