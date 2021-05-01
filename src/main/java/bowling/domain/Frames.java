package bowling.domain;

import java.util.ArrayList;
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
		Frame finalFrame = new FinalFrame(10);
		frames.get(8).linkNextFrame(finalFrame);
		frames.add(finalFrame);
		return new Frames(frames);
	}

	private static List<Frame> normalFrameMake() {
		List<Frame> frames = new ArrayList<>();
		Frame previousFrame = new NormalFrame(1);
		frames.add(previousFrame);
		for (int frameNumber = 2; frameNumber < 10 ; frameNumber++) {
			Frame nextFrame = new NormalFrame(frameNumber);
			frames.add(nextFrame);
			previousFrame.linkNextFrame(nextFrame);
			previousFrame = nextFrame;
		}
		return frames;
	}

	public int currentFrame() {
		return currentFrame;
	}

	public void bowl(int hittingNumber) {
		Frame frame = frames.get(currentFrame - 1);
		if (!frame.isEndFrame()) {
			frame.bowl(hittingNumber);
		}
		if (frame.isEndFrame() && currentFrame < 10) {
			currentFrame = currentFrame + 1;
		}
	}

	public int getScore(int frameNumber) {
		return frames.get(frameNumber-1).getScore();
	}

	public boolean isContinueFrame() {
		return !(currentFrame == 10 && frames.get(currentFrame - 1).isEndFrame());
	}

	public int size() {
		return frames.size();
	}

	public List<Frame> frames() {
		return Collections.unmodifiableList(frames);
	}

}
