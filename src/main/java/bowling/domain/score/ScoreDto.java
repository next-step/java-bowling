package bowling.domain.score;

import bowling.domain.BowlType;

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

}
