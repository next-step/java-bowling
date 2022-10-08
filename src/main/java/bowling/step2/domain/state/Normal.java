package bowling.step2.domain.state;

public class Normal implements State {
    private final int firstFallenPins;
    
    public Normal(final int firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
    
    @Override
    public State bowl(final int secondFallenPins) {
        if (isAllFallenPins(secondFallenPins)) {
            return new Spare(firstFallenPins);
        }
        
        return new Miss(firstFallenPins, secondFallenPins);
    }
    
    private boolean isAllFallenPins(final int secondFallenPins) {
        return COUNT_OF_MAX_PINS == firstFallenPins + secondFallenPins;
    }
}
