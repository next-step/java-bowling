package bowling.domain.state;

public interface State {
    boolean isFinish();

    State bowl(int fallenPinCount);

    String print();
}
