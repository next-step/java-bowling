package bowling.domain;

public enum Mark {
    STRIKE ("X"),
    SPARE ("/"),
    GUTTER ("-");

    public static final int PIN_COUNT_MAX = 10;

    private final String Mark;

    Mark(String Mark) {
        this.Mark = Mark;
    }

    public static String firstMark(PinCount pinCount) {
        if (pinCount.isStrike()) {
            return STRIKE.Mark;
        }

        if (pinCount.isGutter()) {
            return GUTTER.Mark;
        }

        return String.valueOf(pinCount.value());
    }

    public static String secondMark(PinCount firstPinCount, PinCount secondPinCount) {
        if (firstPinCount.isStrike() && secondPinCount.isStrike()) {
            return STRIKE.Mark;
        }

        if (!firstPinCount.isStrike() && firstPinCount.plus(secondPinCount) == PIN_COUNT_MAX) {
            return SPARE.Mark;
        }

        if (secondPinCount.isGutter()) {
            return GUTTER.Mark;
        }

        return String.valueOf(secondPinCount.value());
    }

    public static String thirdMark(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        if (isAllStrike(firstPinCount, secondPinCount, thirdPinCount)
                || isOnlyThirdStrike(firstPinCount, secondPinCount, thirdPinCount)) {
            return STRIKE.Mark;
        }

        if (isThirdSpare(firstPinCount, secondPinCount, thirdPinCount)) {
            return SPARE.Mark;
        }

        if (isThirdGutterWithFirstStrike(firstPinCount, secondPinCount, thirdPinCount)
                || isThirdGutterWithSecondSpare(firstPinCount, secondPinCount, thirdPinCount)) {
            return GUTTER.Mark;
        }

        return String.valueOf(thirdPinCount.value());
    }

    private static boolean isAllStrike(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return firstPinCount.isStrike() && secondPinCount.isStrike() && thirdPinCount.isStrike();
    }

    private static boolean isOnlyThirdStrike(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return !firstPinCount.isStrike() && firstPinCount.plus(secondPinCount) == PIN_COUNT_MAX && thirdPinCount.isStrike();
    }

    private static boolean isThirdSpare(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return firstPinCount.isStrike() && !secondPinCount.isStrike() && secondPinCount.plus(thirdPinCount) == PIN_COUNT_MAX;
    }

    private static boolean isThirdGutterWithFirstStrike(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return firstPinCount.isStrike() && !secondPinCount.isStrike() && thirdPinCount.isGutter();
    }

    private static boolean isThirdGutterWithSecondSpare(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return !firstPinCount.isStrike() && firstPinCount.plus(secondPinCount) == PIN_COUNT_MAX && thirdPinCount.isGutter();
    }
}
