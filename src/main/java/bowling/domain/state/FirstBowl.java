package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class FirstBowl extends RunningState {
	private final Pins first;

	private FirstBowl(Pins first) {
		this.first = first;
	}

	public static State create(Pins pins) {
		return new FirstBowl(pins);
	}

	@Override
	public State bowl(Pins pins) {
		Pins.validateFirstBawl(first, pins);

		if (first.isSpare(pins)) {
			return Spare.create(first, pins);
		}
		return Miss.create(first, pins);
	}

	@Override
	public String symbol() {
		return first.toString();
	}

	@Override
	public Score score() {
		return Score.create(first.value());
	}

	@Override
	public Score calculateAdditionalScore(Score prevScore) {
		return prevScore.bowl(first);
	}
}
