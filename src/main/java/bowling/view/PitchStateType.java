package bowling.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import bowling.domain.state.Miss;
import bowling.domain.state.PitchState;
import bowling.domain.state.Progress;
import bowling.domain.state.Spare;
import bowling.domain.state.Start;
import bowling.domain.state.Strike;

public enum PitchStateType {

	STRIKE(Strike.class, hitPins -> String.format("%s", ScoreFlag.STRIKE.getFlag())),
	SPARE(Spare.class, hitPins -> String.format("%s|/", ScoreFlag.find(hitPins.get(0)))),
	MISS(Miss.class, hitPins -> String.format("%s|%s", ScoreFlag.find(hitPins.get(0)), ScoreFlag.find(hitPins.get(1)))),
	START(Start.class, hitPins -> ""),
	PROGRESS(Progress.class, hitPins -> ScoreFlag.find(hitPins.get(0)));

	private final Class<?> type;
	private final Function<List<Integer>, String> changeFunc;

	PitchStateType(final Class<?> type,
		final Function<List<Integer>, String> changeFunc) {
		this.type = type;
		this.changeFunc = changeFunc;
	}

	public static PitchStateType find(final PitchState pitchState) {
		return Arrays.stream(PitchStateType.values())
			.filter(e -> e.type == pitchState.getClass())
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public String printString(final List<Integer> hitPins) {
		return this.changeFunc.apply(hitPins);
	}
}
