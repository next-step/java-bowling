package bowling.step2.domain.state;

import bowling.step2.domain.Score;

public class Normal extends Running {
    private final Score firstFallenPins;
    
    public Normal(final Score firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
    
    @Override
    public State bowl(final int secondFallenPins) {
        if (isAllFallenPins(secondFallenPins)) {
            return new Spare(firstFallenPins);
        }
        
        return new Miss(firstFallenPins, new Score(secondFallenPins));
    }
    
    private boolean isAllFallenPins(final int secondFallenPins) {
        return firstFallenPins.isAllFallenPins(secondFallenPins);
    }
}
