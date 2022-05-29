package bowling.frame;

import java.util.Optional;

public class GeneralFrame implements Frame {

	public static Frame of(int number) {
		return null;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public void throwBowl(int throwCount) {

	}

	@Override
	public Optional<Frame> nextFrame() {
		return Optional.empty();
	}

	@Override
	public int number() {
		return 0;
	}
}
