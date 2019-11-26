package domain.state;

public class Strike implements State {

	private static final int BOWLING_PINS = 10;
	private static final Strike cachingStrike = new Strike();

	private Strike() {

	}

	public static Strike getInstance() {
		return cachingStrike;
	}

	@Override
	public State nextState(int fallenPinsCount) {
		if (fallenPinsCount == 10) {
			return Strike.getInstance();
		}
		return InProgress.of(fallenPinsCount);
	}

	@Override
	public boolean isRestoredState() {
		return true;
	}

	@Override
	public int getFallenBowlingPins() {
		return BOWLING_PINS;
	}

	@Override
	public boolean isLastStateToDecideScoreLeft() {
		return true;
	}

	@Override
	public int getLeftStatesToCalculateScore() {
		return 2;
	}

	@Override
	public String toSign() {
		return "X";
	}

}
