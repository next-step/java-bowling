package domain.state;

import java.util.Objects;

public class Miss implements State {

	private int bowlingPins;

	private Miss(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Miss of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("Miss에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new Miss(bowlingPins);
	}

	@Override
	public State nextState(int fallenPinsCount) {
		throw new UnsupportedOperationException("MISS는 마지막 상태입니다");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Miss miss = (Miss) o;
		return bowlingPins == miss.bowlingPins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingPins);
	}

}
