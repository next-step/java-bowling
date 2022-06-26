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
        return compareStates(state, READY);
    }

    public static boolean isFirstBowl(State state) {
        return compareStates(state, FIRST_BOWL);
    }

    public static boolean isStrike(State state) {
        return compareStates(state, STRIKE);
    }

    public static boolean isSpare(State state) {
        return compareStates(state, SPARE);
    }

    public static boolean isMiss(State state) {
        return compareStates(state, MISS);
    }

    public static boolean isGutter(State state) {
        return compareStates(state, GUTTER);
    }

    public static boolean isRunning(State state) {
        return compareSuperClassStates(state, "Running");
    }

    public static boolean isFinished(State state) {
        return compareSuperClassStates(state, "Finished");
    }

    private static boolean compareStates(State state, StateEnum referenceState) {
        StateEnum matchedState = Arrays.stream(values())
                                       .filter(stateEnum -> stateEnum.className.equals(state.getClass().getSimpleName()))
                                       .findFirst().orElse(READY);
        return referenceState == matchedState;
    }

    private static boolean compareSuperClassStates(State state, String superClassName) {
        StateEnum matchedState = Arrays.stream(values())
                                       .filter(stateEnum -> stateEnum.className.equals(state.getClass().getSimpleName()))
                                       .findFirst().orElse(READY);
        return superClassName.equals(matchedState.superClassName);
    }
}