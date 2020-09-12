package bowling.pitching.status;

import java.util.Objects;

import bowling.pin.Pins;
import bowling.pitching.PitchingState;


public class PitchingResult {
	private final Pins knockingDownPins;
	private final PitchingState pitchingState;
	private boolean reflectToNextScore;

	private PitchingResult(Pins knockingDownPins, PitchingState pitchingState) {
		this.knockingDownPins = knockingDownPins;
		this.pitchingState = pitchingState;
	}

	public static PitchingResult of(Pins knockingDownPins, PitchingState pitchingState) {
		return new PitchingResult(knockingDownPins, pitchingState);
	}

	public int getKnockingDownPins() {
		return knockingDownPins.getKnockingDownPins();
	}

	public PitchingState getPitchingState() {
		return pitchingState;
	}

	public boolean canMoveNextFrame() {
		return pitchingState.canMoveNextFrame();
	}

	public boolean isStrikeOrSpare() {
		return pitchingState.isStrikeOrSpare();
	}

	public boolean isLastPitchingOfCurrentFrame() {
		return canMoveNextFrame();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PitchingResult that = (PitchingResult) o;
		return Objects.equals(knockingDownPins, that.knockingDownPins) &&
			   pitchingState == that.pitchingState;
	}

	@Override
	public int hashCode() {
		return Objects.hash(knockingDownPins, pitchingState);
	}

	public boolean alreadyReflectToNextScore() {
		return reflectToNextScore;
	}

	public void reflectToNextScore() {
		reflectToNextScore = true;
	}
}
