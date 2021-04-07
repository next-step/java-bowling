package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.exception.UserException;

public class PlayerFrameScore {
	private static final int FINAL_FRAME_INDEX = 10;
	private final List<Frame> frames;
	private final List<FrameScore> scores;

	public PlayerFrameScore() {
		this(new ArrayList<>(), new ArrayList<>());
	}

	public PlayerFrameScore(List<Frame> frames, List<FrameScore> scores) {
		validate(frames);
		this.frames = frames;
		this.scores = scores;
	}

	private void validate(List<Frame> frames) {
		if (frames.size() > 10) {
			throw new UserException(FRAME_MAXIMUM_NINE);
		}
	}

	public boolean isAdd() {
		if (frames.isEmpty()) {
			return true;
		}
		return frames.get(frames.size() - 1).isLast();
	}

	public PlayerFrameScore addFrameScore(Frame frame, List<FrameScore> scores) {
		frames.add(frame);
		return new PlayerFrameScore(frames, init(scores));
	}

	public PlayerFrameScore setFrameScore(Frame frame, List<FrameScore> scores) {
		frames.set(frames.size() - 1, frame);
		return new PlayerFrameScore(frames, init(scores));
	}

	public List<FrameScore> addScore(int score) {
		return scores.stream()
			.map(frameScore -> frameScore.appendScore(score))
			.collect(Collectors.toList());
	}

	public int sum(int index) {
		return IntStream.rangeClosed(0, index)
			.mapToObj(scores::get)
			.mapToInt(FrameScore::getScore)
			.sum();
	}

	public long scoreLastSize() {
		return scores.stream()
			.filter(FrameScore::isShow)
			.count();
	}

	private List<FrameScore> init(List<FrameScore> frameScores) {
		if (isAdd()) {
			frameScores.add(FrameScore.of(frames.get(lastIndex())));
		}
		return frameScores;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public int lastIndex() {
		if (this.frames.isEmpty()) {
			return 0;
		}
		return this.frames.size() - 1;
	}

	public boolean isFinalFrame(int currentIndex) {
		return currentIndex == FINAL_FRAME_INDEX;
	}
}
