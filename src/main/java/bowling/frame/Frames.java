package bowling.frame;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

import bowling.pin.Pins;

public class Frames {

	private static final int START_FRAME_INDEX = 0;
	private static final int FRAMES_COUNT = 10;

	private final List<Frame> frames;
	private int currentFrameIndex;

	private Frames() {
		this.frames = IntStream.range(START_FRAME_INDEX, FRAMES_COUNT - 1)
							   .mapToObj(i -> NormalFrame.of(i + 1))
							   .collect(toList());
		this.frames.add(FinalFrame.newInstance());
		this.currentFrameIndex = START_FRAME_INDEX;
	}

	public static Frames newInstance() {
		return new Frames();
	}

	public boolean isEnd() {
		return currentFrameIndex == FRAMES_COUNT;
	}

	public Frames reflect(Pins pins) {
		Frame currentFrame = frames.get(currentFrameIndex);
		currentFrame.reflect(pins);
		if (currentFrame.finish()) {
			currentFrameIndex++;
		}

		return this;
	}

	public int getCurrentFrameNo() {
		return currentFrameIndex + 1;
	}

	public List<String> getKnockingDownPinsSignsOf(int frameIndex) {
		return frames.get(frameIndex).getKnockingDownPinsSigns();
	}

}
