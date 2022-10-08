package bowling.step2.domain;

public class LeftOverPins {
    private static final int MAX_PINS = 10;
    
    private int leftOverPins;
    
    public LeftOverPins() {
        this.leftOverPins = MAX_PINS;
    }
    
    public void knockDown(final int fallenPins) {
        this.leftOverPins = leftOverPins - fallenPins;
    }
    
    public boolean isExistLeftOverPins() {
        return leftOverPins > 0;
    }
}
