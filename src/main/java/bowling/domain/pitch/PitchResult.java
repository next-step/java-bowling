package bowling.domain.pitch;

import bowling.domain.bowl.type.BowlType;
import bowling.domain.score.Score;

public class PitchResult {

    private static final int MAX_HIT_COUNT = 10;
    private static final int MIN_HIT_COUNT = 0;

    private final int first;
    private final int second;
    private final BowlType bowlType;

    private PitchResult(int first, int second, BowlType bowlType){
        this.first = first;
        this.second = second;
        this.bowlType = bowlType;
    }

    public static PitchResult spare(int first, int second){
        return new PitchResult(first, second, BowlType.SPARE);
    }

    public static PitchResult first(int first){
        return new PitchResult(first, MIN_HIT_COUNT, BowlType.FIRST);
    }

    public static PitchResult strike(){
        return new PitchResult(MAX_HIT_COUNT, 0, BowlType.STRIKE);
    }

    public static PitchResult gutter() {
        return new PitchResult(MIN_HIT_COUNT, MIN_HIT_COUNT, BowlType.GUTTER);
    }

    public static PitchResult miss(int first, int second) {
        return new PitchResult(first, second, BowlType.MISS);
    }

    public static PitchResult ready() {
        return new PitchResult(Score.CANNOT_CALCULATE_SCORE, Score.CANNOT_CALCULATE_SCORE, BowlType.READY);
    }

    public int getFirstHitCount() {
        return first;
    }

    public int getSecondHitCount() {
        return second;
    }

    public BowlType getBowlType() {
        return bowlType;
    }

    @Override
    public String toString(){
        return "hit count: "+first
                +" bowlType: "+bowlType ;
    }
}
