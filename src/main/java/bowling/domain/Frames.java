package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.exception.GameOverException;

public class Frames {
	private static final int MAX_FRAME_COUNT = 10;
	private final List<Frame> frames;

	public Frames() {
		this.frames = new ArrayList<>();
	}

	public void pitch(int count) {
		if (isEnd()) {
			throw new GameOverException();
		}
		Frame frame = findFrame();
		frame.pitch(count);
	}

	private Frame findFrame() {
		if (frames.isEmpty()) {
			NormalFrame frame = NormalFrame.firstFrame();
			frames.add(frame);
			return frame;
		}
		if (getLastFrame().isEnd()) {
			Frame frame = frames.size() == MAX_FRAME_COUNT - 1
				? new FinalFrame()
				: getLastFrame().next();
			frames.add(frame);
			return frame;
		}
		return getLastFrame();
	}

	private boolean isFinalFrame() {
		return frames.size() == MAX_FRAME_COUNT;
	}

	boolean isEnd() {
		return isFinalFrame() && getLastFrame().isEnd();
	}

	private Frame getLastFrame() {
		if (frames.isEmpty()) {
			throw new GameOverException();
		}
		return frames.get(frames.size() - 1);
	}

	public int getIndex() {
		if (frames.isEmpty() || getLastFrame().isEnd()) {
			return frames.size() + 1;
		}
		return frames.size();
	}

	public List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frames frames1 = (Frames)o;
		return Objects.equals(frames, frames1.frames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frames);
	}
}
