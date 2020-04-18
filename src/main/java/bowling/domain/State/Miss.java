package bowling.domain.State;

public class Miss extends Finished {
    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(secondPins);
    }
}
