package bowling.domain.State;

public class Spare extends Finished {
    private final int firstFelledPins;

    public Spare(int firstFelledPins) {
        this.firstFelledPins = firstFelledPins;
    }

    public static State of(int firstFelledPins) {
        return new Spare(firstFelledPins);
    }

    @Override
    public String getDisplayText() {
        return firstFelledPins + DELIMITER + SPARE;
    }
}
