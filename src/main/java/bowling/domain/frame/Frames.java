package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.PitchStates;

public class Frames {

	private static final int MAX_COUNT = 10;

	private final List<Frame> frames;

	private Frames() {
		this.frames = new ArrayList<>(MAX_COUNT);
		this.frames.add(BaseFrame.of());
	}

	private Frames(final List<Frame> frames) {
		this.frames = frames;
	}

	public static Frames of() {
		return new Frames();
	}

	public static Frames of(final List<Frame> frames) {
		return new Frames(frames);
	}

	public void hitPins(final Pins pins) {
		final Frame frame = currentFrame();
		frame.hitPins(pins);
		if (frame instanceof BaseFrame && frame.isFinish()) {
			addFrame((BaseFrame)frame);
		}
	}

	private void addFrame(final BaseFrame frame) {
		final Frame next = (frames.size() < MAX_COUNT - 1) ? BaseFrame.of() : LastFrame.of();
		frames.remove(frame);
		frames.add(frame.create(next));
		frames.add(next);
	}

	public List<PitchStates> getAllPitchStates() {
		return frames.stream()
			.map(Frame::getPitchStates)
			.collect(Collectors.toList());
	}

	public List<Score> getScores() {
		return frames.stream()
			.map(Frame::getScore)
			.collect(Collectors.toList());
	}

	public boolean isFinish() {
		return currentFrame().isFinish();
	}

	public Frame currentFrame() {
		return frames.get(frames.size() - 1);
	}

	public int size() {
		return frames.size();
	}
}
