package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Spare extends EndedState {
	private final Pins first;
	private final Pins second;

	private Spare(Pins first, Pins second) {
		this.first = first;
		this.second = second;
	}

	public static State create(Pins first, Pins second) {
		Pins.validateSpare(first, second);
		return new Spare(first, second);
	}

	@Override
	public String symbol() {
		return (first.isGutter() ? "-" : first) + "|/";
	}

	@Override
	public Score score() {
		return Score.create(10, 1);
	}

	@Override
	public Score calculateAdditionalScore(Score prevScore) {
		prevScore = prevScore.bowl(first.toScore());
		if (prevScore.canCalculateScore()) {
			return prevScore;
		}
		return prevScore.bowl(second.toScore());
	}
}
