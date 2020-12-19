package bowling.domain.score;

import bowling.domain.BowlType;

import java.util.Objects;

public class ScoreDto {

    private final int sumScore;
    private final BowlType bowlType;


    public ScoreDto(int sumScore, BowlType bowlType) {
        this.sumScore = sumScore;
        this.bowlType = bowlType;
    }
    public static ScoreDto init(int value, BowlType bowlType) {
        return new ScoreDto(value, bowlType);
    }

    public int getSumScore() {
        return sumScore;
    }

    public BowlType getBowlType() {
        return bowlType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreDto scoreDto = (ScoreDto) o;
        return sumScore == scoreDto.sumScore && bowlType == scoreDto.bowlType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sumScore, bowlType);
    }
}
