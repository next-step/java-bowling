package domain.state;

public class Gutter implements State {

	private static final int BOWLING_PINS = 0;
	private static final Gutter cachingGutter = new Gutter();


	private Gutter() {

	}

	public static Gutter getInstance() {
		return cachingGutter;
	}

	@Override
	public State nextState(int fallenPinsCount) {
		throw new UnsupportedOperationException("GUTTER는 마지막 상태입니다");
	}

	@Override
	public int getFallenBowlingPins() {
		return BOWLING_PINS;
	}

	@Override
	public boolean isLastStateInFinalFrame() {
		return true;
	}

	@Override
	public boolean isLastStateToDecideScoreLeft() {
		return true;
	}

}
