package bowling.domain;

public enum Symbol {
    STRIKE("X"),
    SPARE("/"),
    GUTTER("-");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public static String ofFirst(PinCount pinCount) {
        if (pinCount.isStrike()) {
            return STRIKE.symbol;
        }

        if (pinCount.isGutter()) {
            return GUTTER.symbol;
        }

        return String.valueOf(pinCount.value());
    }

    public static String ofSecond(PinCount firstPinCount, PinCount secondPinCount) {

        if (firstPinCount.isStrike() && secondPinCount.isStrike()) {
            return STRIKE.symbol;
        }

        if (!firstPinCount.isStrike() && secondPinCount.isSpare(firstPinCount)) {
            return SPARE.symbol;
        }

        if (secondPinCount.isGutter()) {
            return GUTTER.symbol;
        }

        return String.valueOf(secondPinCount.value());
    }

    public static String ofThird(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {

        if (thirdPinCount.isStrike()) {
            return STRIKE.symbol;
        }

        if (isThirdSpare(firstPinCount, secondPinCount, thirdPinCount)) {
            return SPARE.symbol;
        }

        if (thirdPinCount.isGutter()) {
            return GUTTER.symbol;
        }

        return String.valueOf(thirdPinCount.value());
    }

    private static boolean isThirdSpare(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return firstPinCount.isStrike() && !secondPinCount.isStrike() && thirdPinCount.isSpare(secondPinCount);
    }
}