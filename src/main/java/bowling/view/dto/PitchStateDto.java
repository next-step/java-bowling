package bowling.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.common.Pins;
import bowling.domain.state.PitchState;
import bowling.view.PitchStateType;

public class PitchStateDto {

	private final PitchStateType type;
	private final List<Integer> hitPins;

	private PitchStateDto(final PitchState pitchState) {
		this.type = PitchStateType.find(pitchState);
		this.hitPins = pitchState.getHitPins().stream()
			.map(Pins::getHitCount)
			.collect(Collectors.toList());
	}

	public static PitchStateDto of(final PitchState pitchState) {
		return new PitchStateDto(pitchState);
	}

	public String printString() {
		return type.printString(hitPins);
	}
}
