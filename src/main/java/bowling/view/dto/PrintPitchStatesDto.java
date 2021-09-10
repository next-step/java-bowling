package bowling.view.dto;

import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.state.PitchState;

public class PrintPitchStatesDto {

	private final List<PitchStateDto> states;

	private PrintPitchStatesDto(final List<PitchState> pitchStates) {
		this.states = pitchStates.stream()
			.map(PitchStateDto::of)
			.collect(Collectors.toList());
	}

	public static PrintPitchStatesDto of(final List<PitchState> pitchStates) {
		return new PrintPitchStatesDto(pitchStates);
	}

	public String printString() {
		return states.stream()
			.map(PitchStateDto::printString)
			.collect(Collectors.joining("|"));
	}
}
