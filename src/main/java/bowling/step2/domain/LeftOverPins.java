package bowling.step2.domain;

public class LeftOverPins {
    private static final int MAX_PINS = 10;
    
    private final int leftOverPins;
    
    public LeftOverPins() {
        this(MAX_PINS);
    }
    
    public LeftOverPins(final int leftOverPins) {
        this.leftOverPins = leftOverPins;
    }
    
    public LeftOverPins knockDown(final int fallenPins) {
        return new LeftOverPins(this.leftOverPins - fallenPins);
    }
    
    public boolean isExistLeftOverPins() {
        return leftOverPins > 0;
    }
}
