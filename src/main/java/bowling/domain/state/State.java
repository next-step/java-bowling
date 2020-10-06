package bowling.domain.state;

public interface State {
    State pitch(int fallenPins);
}
