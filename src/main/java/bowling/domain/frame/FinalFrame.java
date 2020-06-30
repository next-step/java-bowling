package bowling.domain.frame;

public class FinalFrame implements Frame {

	private final int LAST_INDEX = 10;
	private final int index;

	private FinalFrame() {
		this.index = LAST_INDEX;
	}

	public static FinalFrame of() {
		return new FinalFrame();
	}

	@Override
	public Frame addNextFrame() {
		throw new IllegalArgumentException("마지막 프레임은 다음 프레임을 만들지 않습니다.");
	}
}
