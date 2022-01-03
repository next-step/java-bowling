package bowling.domain.state;

import bowling.domain.Pins;

public class Spare extends Finished {

    private static final String MARKING = "%d|/";

    private final Pins firstPins;
    private final Pins secondPins;


    public Spare(Pins firstPins, Pins secondPins) {
        valid(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public String mark() {
        return String.format(MARKING, firstPins.fallenPins());
    }

    private void valid(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException("스페어가 아닙니다.");
        }
    }

}
