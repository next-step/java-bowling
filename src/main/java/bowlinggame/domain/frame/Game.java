package bowlinggame.domain.frame;

import bowlinggame.dto.PlayerResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

	private List<Frame> frames = new ArrayList<>();

	public Game() {
		frames.add(Frame.first());
	}

	public Game(List<Frame> frames) {
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

	public PlayerResultDto result(String playerName) {
		List<FrameResult> totalResult = frames.stream()
				.map(Frame::result)
				.collect(Collectors.toList());
		return new PlayerResultDto(playerName, totalResult);
	}
}
