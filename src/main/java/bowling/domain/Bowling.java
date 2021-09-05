package bowling.domain;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidProgressBowlingException;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Bowling {

	private static final int MAX_FRAMES_COUNT = 10;
	private static final int FINAL_FRAME_INDEX = MAX_FRAMES_COUNT - 1;

	private final List<Frame> frames;

	private Bowling(final List<Frame> frames) {
		this.frames = frames;
	}

	public static Bowling of() {
		return new Bowling(Collections.singletonList(NormalFrame.of()));
	}

	public boolean possiblePitch() {
		return frames.size() < MAX_FRAMES_COUNT
				|| frames.get(FINAL_FRAME_INDEX).possiblePitch();
	}

	public Bowling pitch(final int pinsCount) {
		return pitch(Pins.of(pinsCount));
	}

	public Bowling pitch(final Pins pins) {
		if (!possiblePitch()) {
			throw new InvalidProgressBowlingException();
		}

		final List<Frame> frames = new ArrayList<>(this.frames);
		final Frame currentFrame = frames.get(frames.size() - 1);

		if (currentFrame.possiblePitch()) {
			frames.remove(currentFrame);
		}

		final Frame nextFrame = nextFrame(frames, currentFrame);
		return new Bowling(Stream.concat(frames.stream(), Stream.of(nextFrame.pitch(pins)))
				.collect(Collectors.toList()));
	}

	private Frame nextFrame(final List<Frame> frames, final Frame frame) {
		if (frame.possiblePitch()) {
			return frame;
		}

		if (frames.size() < FINAL_FRAME_INDEX) {
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

	public List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}
}
