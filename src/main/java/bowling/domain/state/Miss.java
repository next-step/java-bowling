package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Miss extends EndedState {
	private final Pins first;
	private final Pins second;

	private Miss(Pins first, Pins second) {
		this.first = first;
		this.second = second;
	}

	public static State create(Pins first, Pins second) {
		return new Miss(first, second);
	}

	@Override
	public String symbol() {
		return convertIfGutter(first) + "|" + convertIfGutter(second);
	}

	private String convertIfGutter(Pins pins) {
		return pins.isGutter() ? "-" : pins.toString();
	}

	@Override
	public Score score() {
		return first.toScore(second);
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
