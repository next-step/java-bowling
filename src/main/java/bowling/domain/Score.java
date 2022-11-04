package bowling.domain;

public class Score {
    private final int score;
    private final int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score add(int countOfPins) {
        return new Score(score + countOfPins, left - 1);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new UnsupportedOperationException(left + "만큼 남았습니다.");
        }

        return this.score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }
}
