package bowling.dto;

import bowling.domain.score.Score;

public class ScoreDto {

    private int currentScore;

    private boolean isFullyCalculated;

    public ScoreDto(Score score) {
        this.currentScore = score.currentScore();
        this.isFullyCalculated = score.isFullyCalculated();
    }

    public int currentScore() {
        return currentScore;
    }

    public boolean isFullyCalculated() {
        return isFullyCalculated;
    }
}
