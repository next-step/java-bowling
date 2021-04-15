package bowling.dto;

import bowling.domain.BowlingRole;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreDto {

    private final List<Integer> bowlingPoint;
    private final BowlingRole scoreType;

    public ScoreDto(List<Integer> bowlingPoint,BowlingRole scoreType) {
        this.bowlingPoint = bowlingPoint;
        this.scoreType = scoreType;
    }

    public static ScoreDto of(Score score) {
        List<Integer> points = new ArrayList<>();
        points.add(score.firstPoint());
        points.add(score.secondPoint());
        return new ScoreDto(points,score.type());
    }

    public List<Integer> getBowlingPoint() {
        return bowlingPoint;
    }

    public BowlingRole getScoreType() {
        return scoreType;
    }
}
