package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import bowling.domain.point.Point;
import bowling.domain.score.ScoreResult;

import java.util.List;

public class FrameResultDto {
    private final List<Point> points;
    private final ScoreType scoreType;
    private final ScoreResult scoreResult;

    public FrameResultDto(List<Point> points, ScoreType scoreType, ScoreResult scoreResult) {
        this.points = points;
        this.scoreType = scoreType;
        this.scoreResult = scoreResult;
    }

    public List<Point> getPoints() {
        return points;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public ScoreResult getScoreDto() {
        return scoreResult;
    }
}
