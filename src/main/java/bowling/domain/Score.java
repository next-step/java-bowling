package bowling.domain;

public class Score {
    private final int value;
    private final int left;

    public Score(int value, int left) {
        this.value = value;
        this.left = left;
    }

    public Score add(int countOfPins) {
        return new Score(value + countOfPins, left - 1);
    }

    public int getValue() {
        if (!canCalculateScore()) {
            throw new UnsupportedOperationException(left + "만큼 남았습니다.");
        }

        return this.value;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }
}
