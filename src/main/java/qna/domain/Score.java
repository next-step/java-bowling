package qna.domain;

import java.util.Objects;

public class Score {
    public static final int INCOMPUTABLE_SCORE_VALUE = -1;
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 30;
    private static final int READY_REMAINING_PITCHES = -1;
    private static final int MAX_EXTRA_PITCHES = 2;
    private static final int MIN_EXTRA_PITCHES = 0;
    private static final int NO_REMAINING_PITCHES = 0;
    private static final int ONE_PITCH_UNIT = 1;

    private final int value;
    private final int remainingPitches;

    private Score(int value, int remainingPitches) {
        this.value = value;
        this.remainingPitches = remainingPitches;
    }

    public static Score withReady() {
        return new Score(INCOMPUTABLE_SCORE_VALUE, READY_REMAINING_PITCHES);
    }

    public static Score withNonRemainingPitches(int value) {
        return of(value, NO_REMAINING_PITCHES);
    }

    public static Score of(int value, int remainingPitches) {
        validate(value, remainingPitches);
        return new Score(value, remainingPitches);
    }

    private static void validate(int value, int remainingPitches) {
        if (value < MIN_SCORE || value > MAX_SCORE) {
            throw new IllegalArgumentException(String.format("전달된 점수(%d)는 %d <= x <= %d 이어야합니다.", value, MIN_SCORE, MAX_SCORE));
        }
        if (remainingPitches < MIN_EXTRA_PITCHES || remainingPitches > MAX_EXTRA_PITCHES) {
            throw new IllegalArgumentException(
                    String.format("전달된 남은 투구(%d)는 %d <= x <= %d 이어야합니다.", remainingPitches, MIN_EXTRA_PITCHES, MAX_EXTRA_PITCHES)
            );
        }
    }

    public Score bowl(Score nextValue) {
        int sum = this.value + nextValue.value;
        return of(sum, remainingPitches - ONE_PITCH_UNIT);
    }

    public int getFinalValue() {
        if (!hasFinalScore()) {
            throw new IllegalStateException(String.format("투구가 %d회 남아있는 점수입니다. 최종 점수를 구할 수 없습니다.", remainingPitches));
        }
        return this.value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean hasFinalScore() {
        return remainingPitches == NO_REMAINING_PITCHES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value && remainingPitches == score.remainingPitches;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, remainingPitches);
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", priorScoreCount=" + remainingPitches +
                '}';
    }
}
