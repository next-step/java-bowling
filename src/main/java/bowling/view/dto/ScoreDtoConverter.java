package bowling.view.dto;

import bowling.domain.frame.Score;

public class ScoreDtoConverter {
    public static ScoreDto convert(Score score) {
        return new ScoreDto(score.canCalculateScore() ? score.getValue() : null);
    }
}
