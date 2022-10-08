package bowling.step2.domain.state;

public class Miss implements State {
    private final int firstFallenPins;
    private final int secondFallenPins;
    
    public Miss(final int firstFallenPins, final int secondFallenPins) {
        this.firstFallenPins = firstFallenPins;
        this.secondFallenPins = secondFallenPins;
    }
    
    @Override
    public State bowl(final int fallenPins) {
        return null;
    }
}
