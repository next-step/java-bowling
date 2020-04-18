package bowling.domain.State;

public class FirstBowl extends Running {
    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State bowl(int felledPins) {
        Pins secondPins = Pins.bowl(felledPins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }
        return new Miss(firstPins, secondPins);
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc();
    }
}
