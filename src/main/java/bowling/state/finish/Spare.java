package bowling.state.finish;

import bowling.state.State;

public class Spare extends Finished {

    private final int firstFelledPins;

    private Spare(int firstFelledPins) {
        this.firstFelledPins = firstFelledPins;
    }

    public static State of(int firstFelledPins) {
        return new Spare(firstFelledPins);
    }

    @Override
    public String view() {
        return firstFelledPins + DELIMITER + State.SPARE;
    }
}
