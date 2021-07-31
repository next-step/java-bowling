package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class InProgress extends State {

    private final DownedPins downedPins;

    private InProgress(DownedPins downedPins) {
        this.downedPins = downedPins;
    }

    public static InProgress from(DownedPins downedPins) {
        return new InProgress(downedPins);
    }

    @Override
    protected State nextState(DownedPins additionalDownedPins) {
        DownedPins downedPinsTotal = downedPins.add(additionalDownedPins);

        if (downedPinsTotal.isAllDown()) {
            return Spare.from(downedPins);
        }

        return Miss.of(downedPins, additionalDownedPins);
    }
}
