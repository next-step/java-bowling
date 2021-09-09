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
		frames = new ArrayList<>(MAX_COUNT);
		frames.add(BaseFrame.of());
	}

	public static Frames of() {
		return new Frames();
	}

	public void hitPins(final Pins pins) {
		final Frame frame = currentFrame();
		frame.hitPins(pins);
		frame.addFrame(frames);
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

	public boolean isBowlerChange() {
		return currentFrame().isStart();
	}

	private Frame currentFrame() {
		return frames.get(frames.size() - 1);
	}
}
