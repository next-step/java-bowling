package bowling.dto;

import bowling.domain.ScoreType;

public class ShotScoreDto {
    private final ScoreType scoreType;
    private final int score;

    public ShotScoreDto(ScoreType scoreType, int score) {
        this.scoreType = scoreType;
        this.score = score;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public int getScore() {
        return score;
    }
}
