package bowling.step2.domain.state;

import bowling.step2.domain.Score;

public class Spare extends Finished {
    private final Score firstFallenPins;
    
    public Spare(final Score firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
}
