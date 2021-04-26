package bowling.dto;

import bowling.domain.BowlingRole;
import bowling.domain.score.BonusScore;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ScoreDto {

    private final List<Integer> bowlingPoint;
    private final BowlingRole scoreType;

    public ScoreDto(List<Integer> bowlingPoint, BowlingRole scoreType) {
        this.bowlingPoint = bowlingPoint;
        this.scoreType = scoreType;
    }

    public static ScoreDto of(Score score) {
        List<Integer> bowlingPoint = createBowlingPoint(Arrays.asList(score));
        return new ScoreDto(bowlingPoint, score.type());
    }

    public static ScoreDto of(Score score, BonusScore bonusScore) {
        List<Integer> bowlingPoint = createBowlingPoint(Arrays.asList(score));
        if (bonusScore.point() != 0) {
            bowlingPoint.add(bonusScore.point());
        }
        return new ScoreDto(bowlingPoint, score.type());
    }

    public static List<Integer> createBowlingPoint(List<Score> scores) {
        List<Integer> bowlingPoint = new ArrayList<>();
        int bound = scores.size();
        IntStream.range(0, bound).forEach(index -> {
            bowlingPoint.add(scores.get(index).firstPoint());
            bowlingPoint.add(scores.get(index).secondPoint());
        });
        return bowlingPoint;
    }

    public List<Integer> getBowlingPoint() {
        return bowlingPoint;
    }

    public BowlingRole getScoreType() {
        return scoreType;
    }
}
