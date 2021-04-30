package bowling.domain.state;

public interface Progress extends State {

    default boolean isDone() {
        return false;
    }
}
