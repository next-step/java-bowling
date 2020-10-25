package bowling.domain.state;

public interface State {
    boolean isBowl();

    State bowl(int fallenPinCount);

    String print();
}
