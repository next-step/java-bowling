package bowling.domain.state;

import bowling.domain.pin.Pins;

import static bowling.domain.pin.Pins.PIN_COUNT;

public class Trying implements State {
    private static final String TRYING = "%s";
    private static final int GUTTER_NUMBER = 0;
    private static final String GUTTER_SYMBOL = "-";
    private final int firstFallen;

    public Trying(final int firstFallen) {
        validate(firstFallen);
        this.firstFallen = firstFallen;
    }

    private void validate(final int firstFallenPinCount) {
        if (firstFallenPinCount < 0) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀이 음수 값일 수 없습니다. %d", firstFallenPinCount));
        }

        if (firstFallenPinCount >= PIN_COUNT) {
            throw new IllegalArgumentException(String.format("쓰러뜨린 핀이 최대 핀 개수를 초과하였습니다. %d", firstFallenPinCount));
        }
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State bowl(final Pins pins) {
        int secondFallen = pins.fallen();
        if (firstFallen + secondFallen == PIN_COUNT) {
            return new Spare(firstFallen);
        }
        return new Miss(firstFallen, secondFallen);
    }

    @Override
    public String print() {
        if (firstFallen == GUTTER_NUMBER) {
            return String.format(TRYING, GUTTER_SYMBOL);
        }
        return String.format(TRYING, firstFallen);
    }
}
