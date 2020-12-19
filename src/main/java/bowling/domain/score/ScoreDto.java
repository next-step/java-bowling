package bowling.domain.score;

import bowling.domain.BowlType;

public class ScoreDto {

    private final int index;
    private final BowlType bowlType;


    public ScoreDto(int index, BowlType bowlType) {
        this.index = index;
        this.bowlType = bowlType;
    }
    public static ScoreDto init(int value, BowlType bowlType) {
        return new ScoreDto(value, bowlType);
    }

    public int getIndex() {
        return index;
    }

    public BowlType getBowlType() {
        return bowlType;
    }
}
