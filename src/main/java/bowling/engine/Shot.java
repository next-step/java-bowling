package bowling.engine;

public interface Shot extends IntWrapper {
    boolean isSpare();
    boolean isSpareWith(Shot previous);
    Shot add(Shot other);

    default boolean notEquals(Object other) {
        return !equals(other);
    }

}
