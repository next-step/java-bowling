package bowling.domain.frame;

import java.util.List;

public abstract class DefaultFrame implements Frame {

	public void addFrame(final List<Frame> defaultFrames) {
	}

	public boolean isFinish() {
		return false;
	}
}
