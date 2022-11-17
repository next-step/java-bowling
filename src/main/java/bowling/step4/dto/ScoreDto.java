package bowling.step4.dto;

import bowling.step4.domain.Score;

public class ScoreDto {
    public final String score;

    public ScoreDto(String score) {
        this.score = score;
    }

    public static ScoreDto from(Score score) {
        if (score == null) {
            return new ScoreDto("");
        }
        return new ScoreDto(String.valueOf(score.value()));
    }
}
