package bowling.state.finished;

import bowling.state.State;

public class Miss extends Finished {
    private final int firstFelledPins;
    private final int secondFelledPins;

    private Miss(int firstFelledPins, int secondFelledPins) {
        this.firstFelledPins = firstFelledPins;
        this.secondFelledPins = secondFelledPins;
    }

    public static State of(int firstFelledPins, int secondFelledPins) {
        return new Miss(firstFelledPins, secondFelledPins);
    }

    @Override
    public String view() {
        if (secondFelledPins == FELLED_ZERO_PINS) {
            return firstFelledPins + DELIMITER + State.GUTTER;
        }
        return firstFelledPins + DELIMITER + secondFelledPins;
    }
}
