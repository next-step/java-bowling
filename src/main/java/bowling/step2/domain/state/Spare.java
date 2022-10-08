package bowling.step2.domain.state;

public class Spare extends Finished {
    private final int firstFallenPins;
    
    public Spare(final int firstFallenPins) {
        this.firstFallenPins = firstFallenPins;
    }
}
