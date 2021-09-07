package bowling.model;

public class Score {
    private static final int MIN = 0;
    private static final int MAX = 10;

    private final int first;
    private int second;

    public Score(int first) {
        validateFirstScoreRange(first);

        this.first = first;
    }

    private void validateFirstScoreRange(int first) {
        if (first < MIN || first > MAX) {
            throw new IllegalArgumentException(String.format("첫 번쨰 볼링 점수는 %d점 이상 %d점 이하여야 합니다.", MIN, MAX));
        }
    }

    public void setSecond(int second) {
        validateSecondScoreRange(second);
    }

    private void validateSecondScoreRange(int second) {
        if (second < MIN) {
            throw new IllegalArgumentException(String.format("두 번째 볼링 점수가 %d점 미만일 수 없습니다.", MIN));
        }

        int sum = first + second;
        if (sum > MAX) {
            throw new IllegalArgumentException(String.format("총 볼링 점수가 %d점을 초과할 수 없습니다.", MAX));
        }
    }
}
