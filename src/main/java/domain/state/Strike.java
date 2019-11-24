package domain.state;

import java.util.Objects;

public class Strike implements State {

	private static final int FIX_BOWLING_PINS = 10;
	private static final Strike cachingStrike = new Strike(FIX_BOWLING_PINS);

	private int bowlingPins;

	private Strike(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Strike getInstance() {
		return cachingStrike;
	}

	@Override
	public State nextState(int fallenPinsCount) {
		if (fallenPinsCount == 10) {
			return Strike.getInstance();
		}
		return InProgress.of(fallenPinsCount);
	}

	@Override
	public boolean isRestoredState() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Strike strike = (Strike) o;
		return bowlingPins == strike.bowlingPins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingPins);
	}

}
