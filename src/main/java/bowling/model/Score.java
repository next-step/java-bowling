package bowling.model;

public class Score {
    static final int MIN = 0;
    static final int MAX = 10;

    private final int first;
    private final int second;

    public Score(int first, int second) {
        validateScoreRange(first, second);

        this.first = first;
        this.second = second;
    }

    private void validateScoreRange(int first, int second) {
        validateFirstScoreRange(first);
        validateSecondScoreRange(first, second);
    }

    private void validateFirstScoreRange(int first) {
        if (first < MIN || first > MAX) {
            throw new IllegalArgumentException(String.format("첫 번쨰 볼링 점수는 %d점 이상 %d점 이하여야 합니다.", MIN, MAX));
        }
    }

    private void validateSecondScoreRange(int first, int second) {
        if (second < MIN) {
            throw new IllegalArgumentException(String.format("두 번째 볼링 점수가 %d점 미만일 수 없습니다.", MIN));
        }

        int sum = first + second;
        if (sum > MAX) {
            throw new IllegalArgumentException(String.format("총 볼링 점수가 %d점을 초과할 수 없습니다.", MAX));
        }
    }

    public boolean isStrikeOrSpare() {
        int sum = first + second;
        return sum == MAX;
    }
}
