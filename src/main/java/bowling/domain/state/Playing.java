package bowling.domain.state;

public interface Playing extends State {

    @Override
    default boolean isFinish() {
        return false;
    }

    @Override
    default boolean isClear() {
        return false;
    }
}
