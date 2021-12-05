package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public class Ready extends RunningState {
	private Ready() {
	}

	public static State create() {
		return new Ready();
	}

	@Override
	public State bowl(Pins pins) {
		if (pins.isStrike()) {
			return Strike.create();
		}
		return FirstBowl.create(pins);
	}

	@Override
	public String symbol() {
		return "";
	}

	@Override
	public Score score() {
		return Score.createIncalculableScore();
	}

	@Override
	public Score calculateAdditionalScore(Score prevScore) {
		throw new CannotCalculateException();
	}
}
