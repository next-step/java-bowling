package domain.frame;

import domain.Pin;
import domain.frame.state.FinalState;
import domain.frame.state.State;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public class FinalFrame extends AbstractFrame {
	private static final int MAX_FRAME = 10;

	private State state = new FinalState();

	public FinalFrame() {
		super(MAX_FRAME);
	}

	@Override
	public Frame pitch(Pin pin) {
		checkArgument(!isFinished());
		state.tryBowl(pin);
		return this;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public boolean isFinished() {
		return state.isFinished();
	}

	@Override
	public Frame self() {
		return this;
	}

	@Override
	public int getScore() {
		return state.getScore().getScore();
	}
}
