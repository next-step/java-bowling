package bowling_step3.domain;

public interface Frame {
    default void add(Pitch pitch) {

    }

    default int size() {
        return 0;
    }

    default int getFirstOfKnockDown() {
        return 0;
    }

    default int getSecondOfKnockDown() {
        return 0;
    }

    default boolean isFirst() {
        return false;
    }
}
