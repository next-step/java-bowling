package domain.state;

import java.util.Objects;

public class Spare implements State {

	private int bowlingPins;

	private Spare(int bowlingPins) {
		this.bowlingPins = bowlingPins;
	}

	public static Spare of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("Spare에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new Spare(bowlingPins);
	}

	@Override
	public State nextState(int fallenPinsCount) {
		throw new UnsupportedOperationException("SPARE는 마지막 상태입니다");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Spare spare = (Spare) o;
		return bowlingPins == spare.bowlingPins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingPins);
	}

}
