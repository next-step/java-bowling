package bowling.domain.state;

import bowling.domain.Pins;

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
		return first + "|" + (second.isGutter() ? "-" : second);
	}
}
