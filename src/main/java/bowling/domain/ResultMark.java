package bowling.domain;

import bowling.domain.pin.FallenPins;

public enum ResultMark {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    MISS(null),
    EMPTY(" ");

    private final String mark;

    ResultMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return this.mark;
    }

    public static ResultMark getResultMark(FallenPins fallenPins, FallenPins previousFallenPins) {
        if (isEmpty(fallenPins)) {
            return EMPTY;
        }

        if (isGutter(fallenPins)) {
            return GUTTER;
        }

        if (isSpare(fallenPins, previousFallenPins)) {
            return SPARE;
        }

        if (isStrike(fallenPins, previousFallenPins)) {
            return STRIKE;
        }

        return MISS;
    }

    private static boolean isStrike(FallenPins fallenPins, FallenPins previousFallenPins) {
        return isEmpty(previousFallenPins)
                && fallenPins.getCountOfPin() == FallenPins.MAX_COUNT_OF_PIN;
    }

    private static boolean isGutter(FallenPins fallenPins) {
        return fallenPins.getCountOfPin() == 0;
    }

    private static boolean isEmpty(FallenPins fallenPins) {
        return fallenPins == null;
    }

    private static boolean isSpare(FallenPins fallenPins, FallenPins previousFallenPins) {
        return previousFallenPins != null &&
                fallenPins.getCountOfPin() + previousFallenPins.getCountOfPin()
                        == FallenPins.MAX_COUNT_OF_PIN;
    }
}
