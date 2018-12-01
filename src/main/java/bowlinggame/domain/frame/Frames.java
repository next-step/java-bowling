package bowlinggame.domain.frame;

import bowlinggame.domain.PlayerResult;
import bowlinggame.domain.frame.result.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

	private List<Frame> frames = new ArrayList<>();

	public Frames() {
		frames.add(Frame.first());
	}

	public Frames(List<Frame> frames) {
		this.frames = frames;
	}

	public Frame record(int pinCount) {
		Frame currentFrame = getFrame(FrameNumber.of(frames.size()));
		currentFrame = currentFrame.roll(pinCount);

		if (!frames.contains(currentFrame)) {
			frames.add(currentFrame);
		}
		return currentFrame;
	}

	public boolean isFrameOver(FrameNumber frameNumber) {
		if (frameNumber.getNumber() > frames.size()) {
			return false;
		}

		Frame frame = getFrame(frameNumber);
		return frame.isCompleted();
	}

	private Frame getFrame(FrameNumber frameNumber) {
		return frames.stream()
				.filter(frame -> frame.isSameNumber(frameNumber))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("프레임이 존재하지 않습니다."));
	}

	public PlayerResult result(String playerName) {
		List<FrameResult> frameResults = getFrameResults();
		return new PlayerResult(playerName, calculateScore(frameResults));
	}

	private List<FrameResult> getFrameResults() {
		return frames.stream()
				.map(Frame::getFrameResult)
				.collect(Collectors.toList());
	}

	private List<FrameResult> calculateScore(List<FrameResult> frameResults) {
		Score totalScore = Score.init();
		for (FrameResult frameResult : frameResults) {
			totalScore = frameResult.calculateScore(totalScore);
		}
		return frameResults;
	}
}
