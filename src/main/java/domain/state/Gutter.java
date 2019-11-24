package domain.state;

import java.util.Objects;

public class Gutter implements State {

	private static final Gutter cachingGutter = new Gutter(0);
	private int bowlingPinsCount;


	private Gutter(int bowlingPinsCount) {
		this.bowlingPinsCount = bowlingPinsCount;
	}

	public static Gutter getInstance() {
		return cachingGutter;
	}

	@Override
	public State nextState(int fallenPinsCount) {
		throw new UnsupportedOperationException("GUTTER는 마지막 상태입니다");
	}

	@Override
	public boolean isLastState() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Gutter gutter = (Gutter) o;
		return bowlingPinsCount == gutter.bowlingPinsCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingPinsCount);
	}

}
