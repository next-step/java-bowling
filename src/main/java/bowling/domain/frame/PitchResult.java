package bowling.domain.frame;

public class PitchResult {

    private static final int MAX_HIT_COUNT = 10;
    private static final int MIN_HIT_COUNT = 0;

    private final int first;
    private final int second;
    private final boolean isSpare;
    private final boolean isFirst;

    private PitchResult(int first, int second, boolean isSpare, boolean isFirst){
        this.first = first;
        this.second = second;
        this.isSpare = isSpare;
        this.isFirst = isFirst;
    }

    public static PitchResult spare(int first, int second){
        return new PitchResult(first, second,true, false);
    }

    public static PitchResult first(int first){
        return new PitchResult(first, MIN_HIT_COUNT, false, true);
    }

    public static PitchResult strike(){
        return new PitchResult(MAX_HIT_COUNT, 0,false, false);
    }

    public static PitchResult gutter() {
        return new PitchResult(MIN_HIT_COUNT, MIN_HIT_COUNT, false, false);
    }

    public static PitchResult miss(int first, int second) {
        return new PitchResult(first, second, false, false);
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

    public boolean isFirst(){
        return isFirst;
    }

    public boolean isSpare(){
        return isSpare;
    }

    public boolean isStrike(){
        if(getHitCount() != MAX_HIT_COUNT){
            return false;
        }
        return !isSpare;
    }

    public boolean isMiss(){
        if(isFirst){
            return false;
        }
        return MIN_HIT_COUNT < getHitCount() || getHitCount() < MAX_HIT_COUNT;
    }

    public boolean isGutter(){
        if(getHitCount() == MIN_HIT_COUNT){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "hit count: "+first
                +" isSpare: "+ isSpare;
    }
}
