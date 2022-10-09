package bowling.step2.domain;

import bowling.step2.domain.exception.PinCountExceededException;

public class Score {
    private static final int COUNT_OF_MAX_PINS = 10;
    private static final int GUTTER = 0;
    
    private final int fallenPins;
    
    public Score(final int fallenPins) {
        this.fallenPins = fallenPins;
    }
    
    public int add(final int fallenPins) {
        return this.fallenPins + fallenPins;
    }
    
    public boolean isAllFallenPins(final int fallenPins) {
        if (this.fallenPins + fallenPins > COUNT_OF_MAX_PINS) {
            throw new PinCountExceededException();
        }
        
        return this.fallenPins + fallenPins == COUNT_OF_MAX_PINS;
    }
    
    public String display() {
        if (isGutter()) {
            return "-";
        }
        
        return String.valueOf(fallenPins);
    }
    
    private boolean isGutter() {
        return fallenPins == GUTTER;
    }
}
