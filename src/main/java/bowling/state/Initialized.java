package bowling.state;

public class Initialized implements State {

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public State throwBowl(int throwCount) {
		if (Strike.isConstructible(throwCount)) {
			return Strike.INSTANCE;
		}
		return new Remain(throwCount);
	}
}
