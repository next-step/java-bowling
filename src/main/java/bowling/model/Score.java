package bowling.model;

public class Score {
    static final int MIN = 0;
    static final int MAX = 10;

    private final int first;
    private Integer second;

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
        validateEmptySecondScore();
        validateSecondScoreRange(second);
        this.second = second;
    }

    private void validateEmptySecondScore() {
        if (second != null) {
            throw new IllegalArgumentException("두 번째 점수가 이미 존재합니다.");
        }
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
