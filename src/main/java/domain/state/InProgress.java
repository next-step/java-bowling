package domain.state;

import java.util.Objects;

public class InProgress implements State {

	private static final int SPARE_PINS = 10;
	private static final int GUTTER_PINS = 0;

	private int bowlingPinsCount;

	private InProgress(int bowlingPinsCount) {
		this.bowlingPinsCount = bowlingPinsCount;
	}

	public static InProgress of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("InProgress에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new InProgress(bowlingPins);
	}

	@Override
	public State nextState(int fallenPinsCount) {
		if (bowlingPinsCount + fallenPinsCount == SPARE_PINS) {
			return Spare.of(fallenPinsCount);
		}

		if (bowlingPinsCount + fallenPinsCount == GUTTER_PINS) {
			return Gutter.getInstance();
		}

		return Miss.of(fallenPinsCount);
	}

	@Override
	public int getFallenBowlingPins() {
		return bowlingPinsCount;
	}

	@Override
	public int getLeftStatesToCalculateScore() {
		return 1;
	}

	@Override
	public String toSign() {
		return String.valueOf(bowlingPinsCount);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InProgress that = (InProgress) o;
		return bowlingPinsCount == that.bowlingPinsCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingPinsCount);
	}

}
