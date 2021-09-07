package bowling.model;

import static bowling.model.Score.MAX;
import static bowling.model.Score.MIN;

public class BonusScore {
    private final int score;

    public BonusScore(int score) {
        validateScoreRange(score);
        this.score = score;
    }

    private void validateScoreRange(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException(String.format("보너스 볼링 점수는 %d점 이상 %d점 이하여야 합니다.", MIN, MAX));
        }
    }
}
