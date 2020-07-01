package bowling.domain;

import java.util.Arrays;

public enum State {
    GUTTER("-", 0, false),
    READY("0",0,false),
    ONE("1", 1, false),
    TWO("2", 2, false),
    THREE("3", 3, false),
    FOUR("4", 4, false),
    FIVE("5", 5, false),
    SIX("6", 6, false),
    SEVEN("7", 7, false),
    EIGHT("8", 8, false),
    NINE("9", 9, false),
    STRIKE("X", 10, false),
    SPARE("/", 10, true);

    private final String value;
    private final int fallenPins;
    private final boolean isSpare;

    State(String value, int fallenPins, boolean isSpare) {
        this.value = value;
        this.fallenPins = fallenPins;
        this.isSpare = isSpare;
    }

    public static State valueOf(int fallenPins, boolean isSpare) {
        return Arrays.stream(State.values())
                .filter(value -> value.fallenPins == fallenPins && value.isSpare == isSpare)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 값이 아닙니다."));
    }

    public static State bowl(int previousFallenPins, int nextFallenPins, int statesLength) {
        if (checkNormalFrameStrike(nextFallenPins, statesLength)) {
            return STRIKE;
        }

        if (checkGutter(nextFallenPins)) {
            return GUTTER;
        }

        validateFallenPinSum(previousFallenPins, nextFallenPins);

        if (checkNormalFrameSpare(previousFallenPins, nextFallenPins)) {
            return SPARE;
        }

        return State.valueOf(nextFallenPins, false);
    }

    public static State finalBowl(int previousFallenPins, int nextFallenPins, State lastState) {
        validateFallenPinSumForFinalFrame(previousFallenPins, nextFallenPins, lastState);

        if (checkStrike(nextFallenPins, STRIKE)) {
            return STRIKE;
        }

        if (checkGutter(nextFallenPins)) {
            return GUTTER;
        }

        if (checkFinalFrameSpare(previousFallenPins, nextFallenPins, lastState)) {
            return SPARE;
        }

        return State.valueOf(nextFallenPins, false);
    }

    private static boolean checkFinalFrameSpare(int previousFallenPins, int nextFallenPins, State lastState) {
        return lastState != SPARE && checkNormalFrameSpare(previousFallenPins, nextFallenPins);
    }

    private static boolean checkNormalFrameSpare(int previousFallenPins, int nextFallenPins) {
        return previousFallenPins + nextFallenPins == Pin.MAX_PIN;
    }

    private static boolean checkGutter(int nextFallenPins) {
        return checkStrike(nextFallenPins, GUTTER);
    }

    private static boolean checkNormalFrameStrike(int nextFallenPins, int statesLength) {
        return statesLength == 0 && checkStrike(nextFallenPins, STRIKE);
    }

    private static boolean checkStrike(int nextFallenPins, State strike) {
        return strike == State.valueOf(nextFallenPins, false);
    }

    private static void validateFallenPinSumForFinalFrame(int previousFallenPins, int nextFallenPins, State lastState) {
        if(lastState != STRIKE && lastState != SPARE && previousFallenPins + nextFallenPins > Pin.MAX_PIN) {
            throw new FallenPinsSumException();
        }
    }

    private static void validateFallenPinSum(int previousFallenPins, int nextFallenPins) {
        if (previousFallenPins + nextFallenPins > Pin.MAX_PIN) {
            throw new FallenPinsSumException();
        }
    }

    public boolean isStrike() {
        return this.value.equals(STRIKE.value);
    }

    public boolean isSpare() {
        return this.value.equals(SPARE.value);
    }

    public String getValue() {
        return value;
    }

    public int getFallenPins() {
        return fallenPins;
    }
}
