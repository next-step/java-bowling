package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Strike extends EndedState {
	public static State create() {
		return new Strike();
	}

	@Override
	public String symbol() {
		return "X";
	}

	@Override
	public Score score() {
		return Score.create(10, 2);
	}

	@Override
	public Score calculateAdditionalScore(Score prevScore) {
		return prevScore.bowl(Score.create((Pins.MAX_OF_PINS)));
	}
}
