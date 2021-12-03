package bowling.domain.value;

import bowling.annotations.GetterForUI;

import java.util.Objects;

public class Score {
    private static final int STRIKE_ACCUMULATION = 2;
    private static final int SPARE_ACCUMULATION = 1;
    private static final int ZERO_ACCUMULATION = 0;
    private static final int INIT_ACCUMULATION = -1;

    private static final int STRIKE_OR_SPARE_COUNT = 10;

    private final int score;
    private final int accumulation;

    private Score(int score, int accumulation) {
        this.score = score;
        this.accumulation = accumulation;
    }

    public static Score init() {
        return new Score(STRIKE_OR_SPARE_COUNT, INIT_ACCUMULATION);
    }

    public static Score of(int score, int accumulation) {
        return new Score(score, accumulation);
    }

    public static Score ofStrike() {
        return new Score(STRIKE_OR_SPARE_COUNT, STRIKE_ACCUMULATION);
    }

    public static Score ofSpare() {
        return new Score(STRIKE_OR_SPARE_COUNT, SPARE_ACCUMULATION);
    }

    public static Score ofMiss(int score) {
        return new Score(score, ZERO_ACCUMULATION);
    }

    public Score bowl(int countOfPins) {
        return new Score(score + countOfPins, accumulation - 1);
    }

    public boolean canCalculateScore() {
        return ZERO_ACCUMULATION == accumulation;
    }

    @GetterForUI
    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && accumulation == score1.accumulation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, accumulation);
    }
}
