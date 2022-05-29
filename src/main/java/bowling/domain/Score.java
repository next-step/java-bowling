package bowling.domain;

public class Score {
    private static final int NO_REMAINING_PITCHES = 0;
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 30;
    private static final int MAX_REMAINING_PITCHES = 2;
    private static final int MIN_REMAINING_PITCHES = 0;
    public static final int INCALCULABLE_SCORE = -1;
    public static final int INCALCULABLE_PITCHES = -1;

    private final int value;
    private final int remainingPitches;

    public Score(int value, int remainingPitches) {
        validate(value, remainingPitches);
        this.value = value;
        this.remainingPitches = remainingPitches;
    }

    public Score(int value) {
        this(value, NO_REMAINING_PITCHES);
    }

    private void validate(int value, int remainingPitches) {
        validateScore(value);
        validateRemainingPitches(remainingPitches);
    }

    private void validateRemainingPitches(int remainingPitches) {
        if (remainingPitches < MIN_REMAINING_PITCHES || remainingPitches > MAX_REMAINING_PITCHES) {
            throw new IllegalArgumentException(
                    String.format("남은 투구의 수는 %d에서 %d 사이이어야 합니다. (남은 투구의 수: %d)", MIN_REMAINING_PITCHES,
                            MAX_REMAINING_PITCHES, remainingPitches));
        }
    }

    private void validateScore(int value) {
        if (value < MIN_SCORE || value > MAX_SCORE) {
            throw new IllegalArgumentException(
                    String.format("계산된 점수는 %d에서 %d 사이이어야 합니다. (계산된 점수: %d)", MIN_SCORE, MAX_SCORE, value));
        }
    }

    public Score bowl(Score score) {
        int result = this.value + score.value;
        return new Score(result, remainingPitches - 1);
    }

    public int getValue() {
        if(!isCalculatorScore()) {
            throw new IllegalArgumentException(String.format("점수를 계산할 수 없습니다. (남은 투구 수: %d)", remainingPitches));
        }

        return this.value;
    }

    public boolean isCalculatorScore() {
        return remainingPitches == NO_REMAINING_PITCHES;
    }
}
