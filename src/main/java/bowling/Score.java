package bowling;

public class Score {

    private static final int MAX_VALUE = 10;
    private static final int MIN_VALUE = 0;

    private final int first;
    private final int second;

    public Score(int first, int second) {
        validate(first, second);
        this.first = first;
        this.second = second;
    }

    private void validate(int first, int second) {
        if (first + second > MAX_VALUE) {
            throw new IllegalArgumentException("10 이상의 점수를 획득할 수 없습니다.");
        }
    }

    public int getScore() {
        return first + second;
    }

    public Status createFrameStatus() {
        if (first == MAX_VALUE && second == MIN_VALUE) {
            return Status.STRIKE;
        }
        if (first + second == MAX_VALUE) {
            return Status.SPARE;
        }
        if (first + second == MIN_VALUE) {
            return Status.GUTTER;
        }
        if (first + second < MAX_VALUE) {
            return Status.MISS;
        }
        throw new IllegalArgumentException("점수 입력이 잘못 됐습니다.");
    }
}
