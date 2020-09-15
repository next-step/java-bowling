package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.state.Open;
import bowling.domain.state.State;

public class FinalFrameBonusState extends FinalFrameSecondState {

	private final DownedPinCount bonus;

	public FinalFrameBonusState(DownedPinCount first, DownedPinCount second, DownedPinCount bonus) {
		super(first, second);
		this.bonus = bonus;
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
		return this;
	}

	@Override
	public boolean isDone() {
		return true;
	}

	@Override
	public String reportState() {
		return super.reportState() + Open.SPLITTER + convertReportPattern(bonus);
	}
}
