package bowling.step2.domain;

public class Score {
    private static final int COUNT_OF_MAX_PINS = 10;
    
    private final int fallenPins;
    ;
    public Score(final int fallenPins) {
        this.fallenPins = fallenPins;
    }
    
    public int add(final int fallenPins) {
        return this.fallenPins + fallenPins;
    }
    
    public boolean isAllFallenPins(final int fallenPins) {
        return this.fallenPins + fallenPins == COUNT_OF_MAX_PINS;
    }
}
