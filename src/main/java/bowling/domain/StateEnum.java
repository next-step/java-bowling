package bowling.domain;

import java.util.Arrays;

public enum StateEnum {
    STATE("State"),
    READY("Ready"),
    FIRST_BOWL("FirstBowl"),
    STRIKE("Strike"),
    SPARE("Spare"),
    MISS("Miss"),
    GUTTER("Gutter");

    private String className;

    StateEnum(String className) {
        this.className = className;
    }

    public static boolean isReady(State state) {
        return value(state, READY);
    }

    public static boolean isFirstBowl(State state) {
        return value(state, FIRST_BOWL);
    }

    public static boolean isStrike(State state) {
        return value(state, STRIKE);
    }

    public static boolean isSpare(State state) {
        return value(state, SPARE);
    }

    public static boolean isMiss(State state) {
        return value(state, MISS);
    }

    public static boolean isGutter(State state) {
        return value(state, GUTTER);
    }

    private static boolean value(State state, StateEnum referenceState) {
        StateEnum matchedState = Arrays.stream(values())
                                       .filter(stateEnum -> stateEnum.className.equals(state.getClass().getSimpleName()))
                                       .findFirst().orElse(STATE);
        return referenceState == matchedState;
    }
}