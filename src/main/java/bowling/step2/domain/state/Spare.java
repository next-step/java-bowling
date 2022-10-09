package bowling.step2.domain.state;

import bowling.step2.domain.Score;

public class Spare extends Finished {
    private static final String SPARE_DISPLAY = "/";
    private final Score firstFallenPins;
    
    public Spare(final Score firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
    
    @Override
    public String display() {
        return firstFallenPins.display() + DELIMITER + SPARE_DISPLAY;
    }
    
    @Override
    public boolean isSpare() {
        return true;
    }
}
