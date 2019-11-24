package domain.state;

public class Ready implements State {

	private static final int STRIKE_PINS = 10;
	private static Ready cachingReady = new Ready();

	private Ready() {

	}

	public static Ready getInstance() {
		return cachingReady;
	}

	@Override
	public State nextState(int fallenPinsCount) {
		if (fallenPinsCount == STRIKE_PINS) {
			return Strike.getInstance();
		}
		return InProgress.of(fallenPinsCount);
	}

}
