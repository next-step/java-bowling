package bowling.domain.frame;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.domain.score.ScoreDto;

import java.util.List;

public class FrameResultDto {
    private final List<Point> points;
    private final BowlType scoreType;
    private final ScoreDto scoreDto;

    public FrameResultDto(List<Point> points, BowlType scoreType, ScoreDto scoreDto) {
        this.points = points;
        this.scoreType = scoreType;
        this.scoreDto = scoreDto;
    }

    public List<Point> getPoints() {
        return points;
    }

    public BowlType getScoreType() {
        return scoreType;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }
}
