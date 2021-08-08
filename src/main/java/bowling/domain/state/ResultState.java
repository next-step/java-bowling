package bowling.domain.state;

public interface ResultState {
    default boolean isFinish() {
        return false;
    }

    default boolean isMiss() {
        return false;
    }

    default boolean isClean() {
        return false;
    }
}
