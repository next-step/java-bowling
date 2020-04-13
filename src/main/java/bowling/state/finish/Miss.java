package bowling.state.finish;

import bowling.state.State;

public class Miss extends Finished {
    private final int firstFelledPins;
    private final int secondFelledPins;

    private Miss(int firstFelledPins, int secondFelledPins) {
        this.firstFelledPins = firstFelledPins;
        this.secondFelledPins = secondFelledPins;
    }

    public static State of(int firstFelledPins, int secondFelledPins) {
        if (firstFelledPins + secondFelledPins == FELLED_ALL_PINS) {
            throw new IllegalArgumentException("스페어처리 된 상태는 미스상태가 될 수 없습니다.");
        }
        return new Miss(firstFelledPins, secondFelledPins);
    }

    @Override
    public String view() {
        if (secondFelledPins == FELLED_ZERO_PINS) {
            return firstFelledPins + DELIMITER + GUTTER;
        }
        if (firstFelledPins == FELLED_ZERO_PINS) {
            return GUTTER + DELIMITER + secondFelledPins;
        }
        return firstFelledPins + DELIMITER + secondFelledPins;
    }
}
