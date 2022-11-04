package bowling.domain;

public class ScoreV2 {
    private final int score;
    private final int left;

    public ScoreV2(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public ScoreV2 add(int countOfPins) {
        return new ScoreV2(score + countOfPins, left - 1);
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
