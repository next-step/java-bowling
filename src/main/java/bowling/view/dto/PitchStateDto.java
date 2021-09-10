package bowling.view.dto;

import java.util.List;

import bowling.domain.state.PitchState;
import bowling.view.PitchStateType;

public class PitchStateDto {

	private final PitchStateType type;
	private final List<Integer> hitPins;

	private PitchStateDto(final PitchState pitchState) {
		this.type = PitchStateType.find(pitchState);
		this.hitPins = pitchState.getHitPins();
	}

	public static PitchStateDto of(final PitchState pitchState) {
		return new PitchStateDto(pitchState);
	}

	public String printString() {
		return type.printString(hitPins);
	}
}
