package bowling.domain.score;

import java.util.Objects;

public class ScoreResult {

    private final int sumScore;
    private final ScoreType scoreType;


    public ScoreResult(int sumScore, ScoreType scoreType) {
        this.sumScore = sumScore;
        this.scoreType = scoreType;
    }

    public static ScoreResult init(int value, ScoreType scoreType) {
        return new ScoreResult(value, scoreType);
    }

    public static ScoreResult initFinished() {
        return init(0, ScoreType.NONE);
    }

    public static ScoreResult initLastFrameFinished(int sumPoint) {
        return init(sumPoint, ScoreType.END);
    }

    public int getSumScore() {
        return sumScore;
    }

    public ScoreType getBowlType() {
        return scoreType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreResult scoreResult = (ScoreResult) o;
        return sumScore == scoreResult.sumScore && scoreType == scoreResult.scoreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sumScore, scoreType);
    }
}
