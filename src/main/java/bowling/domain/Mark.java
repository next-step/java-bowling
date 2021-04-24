package bowling.domain;

import java.util.List;

public enum Mark {
    STRIKE ("X"),
    SPARE ("/"),
    GUTTER ("-"),
    DELIMETER("|"),
    EMPTY ("");

    public static final int PIN_COUNT_MAX = 10;

    private final String Mark;

    Mark(String Mark) {
        this.Mark = Mark;
    }

    public static String convert(List<PinCount> pinCounts) {
        if (pinCounts.isEmpty()) {
            return EMPTY.Mark;
        }

        if (pinCounts.size() == 1) {
            return firstMark(pinCounts.get(0));
        }

        if (pinCounts.size() == 2) {
            return secondMark(pinCounts.get(0), pinCounts.get(1));
        }

        return thirdMark(pinCounts.get(0), pinCounts.get(1), pinCounts.get(2));
    }

    protected static String firstMark(PinCount pinCount) {
        if (pinCount.isStrike()) {
            return STRIKE.Mark;
        }

        if (pinCount.isGutter()) {
            return GUTTER.Mark;
        }

        return String.valueOf(pinCount.value());
    }

    protected static String secondMark(PinCount firstPinCount, PinCount secondPinCount) {
        String firstMark = firstMark(firstPinCount);

        if (firstPinCount.isStrike() && secondPinCount.isStrike()) {
            return firstMark + DELIMETER.Mark + STRIKE.Mark;
        }

        if (!firstPinCount.isStrike() && firstPinCount.plus(secondPinCount) == PIN_COUNT_MAX) {
            return firstMark + DELIMETER.Mark + SPARE.Mark;
        }

        if (secondPinCount.isGutter()) {
            return firstMark + DELIMETER.Mark + GUTTER.Mark;
        }

        return firstMark + DELIMETER.Mark + secondPinCount.value();
    }

    protected static String thirdMark(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        String secondMark = secondMark(firstPinCount, secondPinCount);

        if (isAllStrike(firstPinCount, secondPinCount, thirdPinCount)
                || isOnlyThirdStrike(firstPinCount, secondPinCount, thirdPinCount)) {
            return secondMark + DELIMETER.Mark + STRIKE.Mark;
        }

        if (isThirdSpare(firstPinCount, secondPinCount, thirdPinCount)) {
            return secondMark + DELIMETER.Mark + SPARE.Mark;
        }

        if (isThirdGutterWithFirstStrike(firstPinCount, secondPinCount, thirdPinCount)
                || isThirdGutterWithSecondSpare(firstPinCount, secondPinCount, thirdPinCount)
                || isThirdGutterWithFirstAndSecondStrike(firstPinCount, secondPinCount, thirdPinCount)){
            return secondMark + DELIMETER.Mark + GUTTER.Mark;
        }

        return secondMark + DELIMETER.Mark + thirdPinCount.value();
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

    private static boolean isThirdGutterWithFirstAndSecondStrike(PinCount firstPinCount, PinCount secondPinCount, PinCount thirdPinCount) {
        return firstPinCount.isStrike() && secondPinCount.isStrike() && thirdPinCount.isGutter();
    }
}
