package bowling.domain.dto;

import bowling.domain.score.Score;

public class ScoreDto {

    private final int score;

    private ScoreDto(final int score) {
        this.score = score;
    }

    public static ScoreDto of(final Score score) {
        return new ScoreDto(score.getScore());
    }

    public int getScore() {
        return this.score;
    }
}
