package bowling.domain.dto;

import bowling.domain.score.Score;
import bowling.domain.score.ScoreType;

public class ScoreResultDto {
    private final ScoreType scoreType;
    private final int point;

    public ScoreResultDto(Score score) {
        this.scoreType = score.getScoreType();
        this.point = score.getPoint();
    }

    public String scoreToString() {
        return scoreType.pointToScore(point);
    }
}
