package domain.state;

import java.util.Objects;

public class Spare implements State {

	private int bowlingPinsCount;

	private Spare(int bowlingPins) {
		this.bowlingPinsCount = bowlingPins;
	}

	public static Spare of(int bowlingPins) {
		if (bowlingPins == 10) {
			throw new IllegalArgumentException("Spare에서 10개를 쓰러뜨릴 수 없습니다");
		}
		return new Spare(bowlingPins);
	}

	@Override
	public State nextState(int fallenPinsCount) {
		if (fallenPinsCount == 10) {
			return Strike.getInstance();
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
	public boolean isLastStateToDecideScoreLeft() {
		return true;
	}

	@Override
	public boolean isRestoredState() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Spare spare = (Spare) o;
		return bowlingPinsCount == spare.bowlingPinsCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bowlingPinsCount);
	}

}
