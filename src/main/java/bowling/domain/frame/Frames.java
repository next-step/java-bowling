package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidProgressBowlingException;

public class Frames {

	private static final int MAX_COUNT = 10;
	private static final int FINAL_INDEX = MAX_COUNT - 1;

	private final List<Frame> frames;

	public Frames(final List<Frame> frames) {
		this.frames = frames;
	}

	public Frames(final Frames frames) {
		this.frames = new ArrayList<>(frames.getFrames());
	}

	public boolean possiblePitch() {
		return frames.size() < MAX_COUNT
			|| frames.get(FINAL_INDEX).possiblePitch();
	}

	public Frames pitch(final Pins pins) {
		if (!possiblePitch()) {
			throw new InvalidProgressBowlingException();
		}

		final List<Frame> frames = new ArrayList<>(this.frames);
		final Frame lastFrame = frames.get(frames.size() - 1);

		if (lastFrame.possiblePitch()) {
			frames.remove(lastFrame);
		}

		frames.add(nextFrame(frames, lastFrame).pitch(pins));
		return new Frames(frames);
	}

	private Frame nextFrame(final List<Frame> frames, final Frame frame) {
		if (frame.possiblePitch()) {
			return frame;
		}

		if (frames.size() < FINAL_INDEX) {
			return frame.next();
		}

		return frame.last();
	}

	public int currentFrameIndex() {
		final int lastFrameIndex = frames.size() - 1;
		return (frames.get(lastFrameIndex).possiblePitch())
			? lastFrameIndex + 1
			: lastFrameIndex + 2;
	}

	public int restFrameCount() {
		return 10 - frames.size();
	}

	public int size() {
		return frames.size();
	}

	public Frame get(final int index) {
		return frames.get(index);
	}

	public int indexOf(final Object obj) {
		return frames.indexOf(obj);
	}

	public List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}
}
