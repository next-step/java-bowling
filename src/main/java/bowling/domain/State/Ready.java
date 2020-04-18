package bowling.domain.State;

public class Ready extends Running {
    @Override
    public State bowl(int felledPins) {
        Pins firstPins = Pins.bowl(felledPins);
        if (firstPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(firstPins);
    }

    @Override
    public String getDesc() {
        return STANDBY;
    }
}
