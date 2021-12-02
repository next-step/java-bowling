package bowling.domain.state;

public class Strike extends EndedState {
	public static State create() {
		return new Strike();
	}

	@Override
	public String symbol() {
		return "X";
	}
}
