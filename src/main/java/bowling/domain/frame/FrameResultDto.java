package bowling.domain.frame;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.domain.score.ScoreDto;

import java.util.List;

public class FrameResultDto {
    private final List<Point> points;
    private final BowlType scoreType;
    private final ScoreDto score;

    public FrameResultDto(List<Point> points, BowlType scoreType, ScoreDto score) {
        this.points = points;
        this.scoreType = scoreType;
        this.score = score;
    }

    public List<Point> getPoints() {
        return points;
    }

    public BowlType getScoreType() {
        return scoreType;
    }

}
