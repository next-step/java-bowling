package bowling.state;

public interface State {

	boolean isEnd();

	State throwBowl(int throwCount);
}
