package bowling.domain.score;

import java.util.Objects;

public class ScoreDto {

    private final int sumScore;
    private final ScoreType scoreType;


    public ScoreDto(int sumScore, ScoreType scoreType) {
        this.sumScore = sumScore;
        this.scoreType = scoreType;
    }
    public static ScoreDto init(int value, ScoreType scoreType) {
        return new ScoreDto(value, scoreType);
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
        ScoreDto scoreDto = (ScoreDto) o;
        return sumScore == scoreDto.sumScore && scoreType == scoreDto.scoreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sumScore, scoreType);
    }
}
