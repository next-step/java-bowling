package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
	private final List<Frame> frames;
	private int currentFrame;

	public Frames(List<Frame> frames) {
		this.frames = frames;
		this.currentFrame = 1;
	}

	public static Frames init() {
		List<Frame> frames = normalFrameMake();
		frames.add(new FinalFrame(10));
		return new Frames(frames);
	}

	private static List<Frame> normalFrameMake() {
		return IntStream.rangeClosed(1, 9)
			.mapToObj(frameNumber -> new NormalFrame(frameNumber))
			.collect(Collectors.toList());
	}

	public int currentFrame() {
		return currentFrame;
	}
	public void bowl(int hittingNumber) {
		Frame frame = frames.get(currentFrame - 1);
		frame.bowl(hittingNumber);
		if (frame.isEndFrame() && currentFrame < 10) {
			currentFrame = currentFrame + 1;
		}
	}

	public boolean isContinueFrame() {
		return !(currentFrame == 10 && frames.get(currentFrame-1).isEndFrame());
	}

	public int size() {
		return frames.size();
	}

	public List<Frame> frames() {
		return Collections.unmodifiableList(frames);
	}

	public PinStatus currentScore() {
		return frames.get(currentFrame-1).pinStatus();
	}
}
