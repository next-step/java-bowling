package bowling.domain.value;

import bowling.annotations.GetterForUI;

import java.util.Objects;

public class Score {
    private static final int STRIKE_ACCUMULATION_COUNT = 2;
    private static final int SPARE_ACCUMULATION_COUNT = 1;
    private static final int ZERO_ACCUMULATION_COUNT = 0;
    private static final int INIT_ACCUMULATION_COUNT = -1;

    private static final int STRIKE_OR_SPARE_COUNT = 10;

    private final int score;
    private final int accumulationCount;

    private Score(int score, int accumulationCount) {
        this.score = score;
        this.accumulationCount = accumulationCount;
    }

    public static Score init() {
        return new Score(STRIKE_OR_SPARE_COUNT, INIT_ACCUMULATION_COUNT);
    }

    public static Score of(int score, int accumulationCount) {
        return new Score(score, accumulationCount);
    }

    public static Score ofStrike() {
        return new Score(STRIKE_OR_SPARE_COUNT, STRIKE_ACCUMULATION_COUNT);
    }

    public static Score ofSpare() {
        return new Score(STRIKE_OR_SPARE_COUNT, SPARE_ACCUMULATION_COUNT);
    }

    public static Score ofMiss(int score) {
        return new Score(score, ZERO_ACCUMULATION_COUNT);
    }

    public Score accumulateScore(int countOfPins) {
        return new Score(score + countOfPins, accumulationCount - 1);
    }

    public boolean canCalculateScore() {
        return ZERO_ACCUMULATION_COUNT == accumulationCount;
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
        return score == score1.score && accumulationCount == score1.accumulationCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, accumulationCount);
    }
}
