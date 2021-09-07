package bowling.model;

public class Score {
    private static final int MIN_FIRST = 0;
    private static final int MAX_FIRST = 10;

    private final int first;
    private int second;

    public Score(int first) {
        validateFirstScoreRange(first);

        this.first = first;
    }

    private void validateFirstScoreRange(int first) {
        if (first < MIN_FIRST || first > MAX_FIRST) {
            throw new IllegalArgumentException(String.format("첫 번쨰 볼링 점수는 %d점 이상 %d점 이하여야 합니다.",
                    MIN_FIRST, MAX_FIRST));
        }
    }
}
