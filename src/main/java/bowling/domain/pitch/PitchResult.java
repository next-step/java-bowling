package bowling.domain.pitch;

public class PitchResult {

    private static final int MAX_HIT_COUNT = 10;
    private static final int MIN_HIT_COUNT = 0;

    private final int first;
    private final int second;
    private final boolean firstBowl;

    private PitchResult(int first, int second, boolean firstBowl){
        this.first = first;
        this.second = second;
        this.firstBowl = firstBowl;
    }

    public static PitchResult spare(int first, int second){
        return new PitchResult(first, second, false);
    }

    public static PitchResult first(int first){
        return new PitchResult(first, MIN_HIT_COUNT, true);
    }

    public static PitchResult strike(){
        return new PitchResult(MAX_HIT_COUNT, 0, false);
    }

    public static PitchResult gutter() {
        return new PitchResult(MIN_HIT_COUNT, MIN_HIT_COUNT, false);
    }

    public static PitchResult miss(int first, int second) {
        return new PitchResult(first, second, false);
    }

    public int getHitCount() {
        return first+second;
    }

    public int getFirstHitCount() {
        return first;
    }

    public int getSecondHitCount() {
        return second;
    }

    public boolean isSpare(){
        if(getHitCount() != MAX_HIT_COUNT){
            return false;
        }
        return first != MAX_HIT_COUNT;
    }

    public boolean isStrike(){
        if(getHitCount() != MAX_HIT_COUNT){
            return false;
        }
        return first == MAX_HIT_COUNT;
    }

    public boolean isMiss(){
        if(firstBowl){
            return false;
        }
        return MIN_HIT_COUNT < getHitCount() || getHitCount() < MAX_HIT_COUNT;
    }

    public boolean isGutter(){
        return getHitCount() == MIN_HIT_COUNT;
    }

    @Override
    public String toString(){
        return "hit count: "+first
                +" firstBowl: "+ firstBowl;
    }
}
