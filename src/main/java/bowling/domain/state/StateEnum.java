package bowling.domain.state;

import java.util.Arrays;

public enum StateEnum {
    READY("Ready", "Running"),
    FIRST_BOWL("FirstBowl", "Running"),
    STRIKE("Strike", "Finished"),
    SPARE("Spare", "Finished"),
    MISS("Miss", "Finished"),
    GUTTER("Gutter", "Running");

    private String className;
    private String superClassName;

    StateEnum(String className, String superClassName) {
        this.className = className;
        this.superClassName = superClassName;
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

    public static boolean isRunning(State state) {
        return valueOfSuperClass(state, "Running");
    }

    public static boolean isFinished(State state) {
        return valueOfSuperClass(state, "Finished");
    }

    private static boolean value(State state, StateEnum referenceState) {
        StateEnum matchedState = Arrays.stream(values())
                                       .filter(stateEnum -> stateEnum.className.equals(state.getClass().getSimpleName()))
                                       .findFirst().orElse(READY);
        return referenceState == matchedState;
    }

    private static boolean valueOfSuperClass(State state, String superClassName) {
        StateEnum matchedState = Arrays.stream(values())
                                       .filter(stateEnum -> stateEnum.className.equals(state.getClass().getSimpleName()))
                                       .findFirst().orElse(READY);
        return superClassName.equals(matchedState.superClassName);
    }
}