package bowling.step2.domain;

import bowling.step2.domain.exception.PinCountExceededException;

import java.util.Objects;

public class Score {
    private static final int COUNT_OF_MAX_PINS = 10;
    private static final int GUTTER = 0;
    
    private final int fallenPins;
    private final boolean isSpare;
    
    public Score(final int fallenPins) {
        this(fallenPins, false);
    }
    
    public Score(final int fallenPins, final boolean isSpare) {
        this.fallenPins = fallenPins;
        this.isSpare = isSpare;
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
    
    public int getFallenPins() {
        return fallenPins;
    }
    
    public boolean isStrike() {
        return fallenPins == COUNT_OF_MAX_PINS;
    }
    
    public boolean isSpare() {
        return isSpare;
    }
    
    public boolean isGutter() {
        return fallenPins == GUTTER;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score = (Score) o;
        return fallenPins == score.fallenPins;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }
}
