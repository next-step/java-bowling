package domain.state;

public interface State {

	State nextState(int fallenPinsCount);

}
