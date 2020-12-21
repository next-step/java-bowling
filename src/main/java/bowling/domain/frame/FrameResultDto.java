package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import bowling.domain.point.Point;
import bowling.domain.score.ScoreDto;

import java.util.List;

public class FrameResultDto {
    private final List<Point> points;
    private final ScoreType scoreType;
    private final ScoreDto scoreDto;

    public FrameResultDto(List<Point> points, ScoreType scoreType, ScoreDto scoreDto) {
        this.points = points;
        this.scoreType = scoreType;
        this.scoreDto = scoreDto;
    }

    public List<Point> getPoints() {
        return points;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }
}
