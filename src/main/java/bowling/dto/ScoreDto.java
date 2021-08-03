package bowling.dto;

import bowling.domain.score.Score;

public class ScoreDto {
    private final int score;

    private ScoreDto(int score) {
        this.score = score;
    }

    public static ScoreDto from(Score score) {
        return new ScoreDto(score.toInt());
    }

    public int getScore() {
        return score;
    }
}
