package domain.frame;

import domain.BowlingPins;
import domain.states.States;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Frames {

	private final static int NORMAL_FRAME_COUNT = 9;
	private final static int FINAL_FRAME_INDEX = 10;

	private final List<Frame> frames;
	private int currentFrameIndex;

	private Frames() {
		List<Frame> frames = new ArrayList<>();
		frames.add(NormalFrame.of(true));
		for (int i = 1; i < NORMAL_FRAME_COUNT; i++) {
			frames.add(NormalFrame.of(false));
		}
		frames.add(FinalFrame.newInstance());
		this.frames = frames;
		this.currentFrameIndex = 0;
	}

	public static Frames newInstance() {
		return new Frames();
	}

	public Frame roll(BowlingPins pins) {
		Frame currentFrame = frames.get(currentFrameIndex);
		currentFrame.roll(pins);
		return currentFrame;
	}

	public void moveToNextFrame() {
		currentFrameIndex++;
	}

	public boolean isEnd() {
		return currentFrameIndex == FINAL_FRAME_INDEX;
	}

	public int getCurrentFrameIndex() {
		return currentFrameIndex;
	}

	public List<States> getStates() {
		return Collections.unmodifiableList(frames.stream()
				.map(Frame::getStates)
				.collect(Collectors.toList()));
	}

	public List<Optional<Integer>> getScores() {
		return frames.stream()
				.map(Frame::getOptionalScore)
				.collect(Collectors.toList());
	}

}
