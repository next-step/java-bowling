package bowling.domain.state;

import static bowling.domain.pin.Pins.PIN_COUNT;

public class Spare extends Finished {
    private static final String SPARE = "%s|/";
    private final int firstFallenPinCount;

    public Spare(final int firstFallenPinCount) {
        validate(firstFallenPinCount);
        this.firstFallenPinCount = firstFallenPinCount;
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
    public String print() {
        String spareFormat = String.format(SPARE, firstFallenPinCount);
        return convertGutter(spareFormat);
    }

    private String convertGutter(final String spareFormat) {
        return spareFormat.replaceAll("0", "-");
    }
}
