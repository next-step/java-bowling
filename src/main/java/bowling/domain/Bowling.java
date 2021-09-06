package bowling.domain;

import java.util.Collections;
import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;

public final class Bowling {

	private final Frames frames;

	private Bowling(final List<Frame> frames) {
		this.frames = new Frames(frames);
	}

	private Bowling(final Frames frames) {
		this.frames = frames;
	}

	public static Bowling of() {
		return new Bowling(Collections.singletonList(NormalFrame.of()));
	}

	public boolean possiblePitch() {
		return frames.possiblePitch();
	}

	public Bowling pitch(final int pinsCount) {
		return pitch(Pins.of(pinsCount));
	}

	public Bowling pitch(final Pins pins) {
		return new Bowling(frames.pitch(pins));
	}

	public int currentFrameIndex() {
		return frames.currentFrameIndex();
	}

	public Frames getFrames() {
		return frames;
	}
}
