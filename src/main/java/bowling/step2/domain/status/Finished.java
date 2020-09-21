package bowling.step2.domain.status;

public abstract class Finished implements Status {

	@Override
	public Status bowling(int pin) {
		//throw new IllegalArgumentException("더 이상 이번 프레임에서 던질 수 있는 기회가 없습니다.");
		return this;
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
