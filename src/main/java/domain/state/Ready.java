package domain.state;

public class Ready implements State {

	private static final int STRIKE_PINS = 10;

	private Ready() {

	}

	public static Ready getInstance() {
		return LazyHolder.INSTANCE;
	}

	@Override
	public int getFallenBowlingPins() {
		return 0;
	}

	@Override
	public State nextState(int fallenPinsCount) {
		if (fallenPinsCount == STRIKE_PINS) {
			return Strike.getInstance();
		}
		return InProgress.of(fallenPinsCount);
	}

	@Override
	public String toSign() {
		return " ";
	}

	private static class LazyHolder {
		private static final Ready INSTANCE = new Ready();
	}

}
