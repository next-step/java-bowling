package domain.state;

import java.util.Objects;

public class Strike implements State {

	private static final int FIX_BOWLING_PINS = 10;

	private int bowlingPins;

	private Strike(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Strike newInstance() {
		return new Strike(FIX_BOWLING_PINS);
	}

	public static Strike of(int bowlingPins) {
		if (bowlingPins != FIX_BOWLING_PINS) {
			throw new IllegalArgumentException("Strike는 10개를 쓰러뜨린 경우입니다");
		}
		return new Strike(bowlingPins);
	}

	@Override
	public State nextState(int fallenPinsCount) {
		throw new UnsupportedOperationException("STRIKE는 마지막 상태입니다");
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
