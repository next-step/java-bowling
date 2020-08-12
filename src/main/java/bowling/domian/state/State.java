package bowling.domian.state;

public interface State {
    State bowl(int falledPinsCount);
}
