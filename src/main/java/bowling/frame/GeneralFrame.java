package bowling.frame;

import java.util.Objects;
import java.util.Optional;

import bowling.state.Initialized;
import bowling.state.State;
import bowling.util.Validator;

public class GeneralFrame implements Frame {

	private static final int MAX_GENERAL_FRAME_NUMBER = Frame.MAX_FRAME_NUMBER - 1;

	private final int number;
	private State state;

	private GeneralFrame(int number, State state) {
		Validator.min(MIN_FRAME_NUMBER, number,
			String.format("프레임의 번호의 최솟값(%d) 보다 입력(%d)이 작습니다.", MIN_FRAME_NUMBER, number));
		Validator.max(MAX_GENERAL_FRAME_NUMBER, number,
			String.format("일반 프레임의 번호의 최댓값(%d) 보다 입력(%d)이 큽니다.", MAX_GENERAL_FRAME_NUMBER, number));
		this.number = number;
		this.state = state;
	}

	public static GeneralFrame initialized(int number) {
		return new GeneralFrame(number, Initialized.getInstance());
	}

	@Override
	public boolean isEnd() {
		return state.isEnd();
	}

	@Override
	public void throwBowl(int throwCount) {
		this.state = state.throwBowl(throwCount);
	}

	@Override
	public Optional<Frame> nextFrame() {
		if (isEnd()) {
			return Optional.of(createFrame());
		}
		return Optional.empty();
	}

	private Frame createFrame() {
		if (this.number == MAX_GENERAL_FRAME_NUMBER) {
			return new EndFrame();
		}
		return GeneralFrame.initialized(this.number + 1);
	}

	@Override
	public int number() {
		return this.number;
	}

	@Override
	public String symbol() {
		return state.symbol();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GeneralFrame that = (GeneralFrame)o;
		return number == that.number && Objects.equals(state, that.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, state);
	}
}
