package bowling.model;

public class FrameScore {
    private static final int MAX_TOTAL_SCORE = 10;
    private static final Score EMPTY_SCORE = null;

    private final Score first;
    private final Score second;

    private FrameScore(Score first, Score second) {
        this.first = first;
        this.second = second;
    }

    public static FrameScore first(int first) {
        return new FrameScore(Score.of(first), EMPTY_SCORE);
    }

    public FrameScore second(int second) {
        validateNotStrike();
        validateSecondScoreRange(first.getValue(), second);
        return new FrameScore(first, Score.of(second));
    }

    private void validateNotStrike() {
        if (isStrike()) {
            throw new IllegalArgumentException("이미 스트라이크이기 때문에 두 번째 점수를 생성할 수 없습니다.");
        }
    }

    private boolean isStrike() {
        return first.isMax();
    }

    private void validateSecondScoreRange(int first, int second) {
        int totalScore = first + second;
        if (totalScore > MAX_TOTAL_SCORE) {
            throw new IllegalArgumentException(String.format("총 볼링 점수가 %d점을 초과할 수 없습니다.", MAX_TOTAL_SCORE));
        }
    }

    public Score getFirst() {
        return first;
    }

    public Score getSecond() {
        return second;
    }
}
