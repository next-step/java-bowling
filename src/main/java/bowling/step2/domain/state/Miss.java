package bowling.step2.domain.state;

import bowling.step2.domain.Score;

public class Miss extends Finished{
    private final Score firstFallenPins;
    private final Score secondFallenPins;
    
    public Miss(final Score firstFallenPins, final Score secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
    }
}
