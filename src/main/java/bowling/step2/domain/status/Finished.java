package bowling.step2.domain.status;

public abstract class Finished implements Status {

	@Override
	public Status bowling(int pin) {
		return this;
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
