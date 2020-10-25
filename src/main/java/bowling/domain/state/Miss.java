package bowling.domain.state;

import static bowling.domain.BowlingGame.PIN_COUNT;

public class Miss extends Finished {
    private static final String MISS = "%s|%s";
    private static final int GUTTER_NUMBER = 0;
    private static final String GUTTER_SYMBOL = "-";
    private final int firstFallenPinCount;
    private final int secondFallenPinCount;

    public Miss(final int firstFallenPinCount, final int secondFallenPinCount) {
        validate(firstFallenPinCount, secondFallenPinCount);
        this.firstFallenPinCount = firstFallenPinCount;
        this.secondFallenPinCount = secondFallenPinCount;
    }

    private void validate(final int firstFallenPinCount, final int secondFallenPinCount) {
        if (firstFallenPinCount < 0 || secondFallenPinCount < 0) {
            throw new IllegalArgumentException(
                    String.format("쓰러뜨린 핀이 음수 값일 수 없습니다. %d %d", firstFallenPinCount, secondFallenPinCount));
        }

        if (firstFallenPinCount + secondFallenPinCount > PIN_COUNT) {
            throw new IllegalArgumentException(
                    String.format("쓰러뜨린 핀이 최대 핀 개수를 초과하였습니다. %d %d", firstFallenPinCount, secondFallenPinCount));
        }
    }

    @Override
    public String print() {
        String missFormat = String.format(MISS, firstFallenPinCount, secondFallenPinCount);
        return convertGutter(missFormat);
    }

    private String convertGutter(final String sparePrint) {
        return sparePrint.replaceAll(String.valueOf(GUTTER_NUMBER), GUTTER_SYMBOL);
    }
}
