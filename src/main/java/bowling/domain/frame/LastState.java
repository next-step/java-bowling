package bowling.domain.frame;

public interface LastState extends State {
    default State record(int score) {
        throw new RuntimeException();
    }

    default boolean isFinished() {
        return true;
    }
}
