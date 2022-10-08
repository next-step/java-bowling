package bowling.step2.domain.state;

public interface State {
    State bowl(int fallenPins);
}
