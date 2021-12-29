package bowling.engine;

public interface Shot extends IntWrapper, StringWrapper {
    boolean isSpare();
    default boolean notEquals(Object other) {
        return !equals(other);
    }
}
