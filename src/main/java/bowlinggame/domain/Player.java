package bowlinggame.domain;

import bowlinggame.domain.frame.Frame;
import bowlinggame.domain.frame.Pin;
import bowlinggame.dto.FrameResultDto;
import bowlinggame.dto.PlayerResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

	public static final int MAX_NAME_LENGTH = 3;

	private String name;
	private List<Frame> frames = new ArrayList<>();

	public Player(String name) {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("이름은 3글자까지 가능합니다.");
		}

		this.name = name;
		this.frames.add(Frame.of(Frame.FIRST_FRAME));
	}

	public PlayerResultDto roll(int pinCount) {
		Pin pin = Pin.from(pinCount);
		Frame currentFrame = getCurrentFrame().roll(pin);
		if (!frames.contains(currentFrame)) {
			frames.add(currentFrame);
		}
		return getPlayerResult();
	}

	private Frame getCurrentFrame() {
		return frames.get(frames.size() - 1);
	}

	public PlayerResultDto getPlayerResult() {
		List<FrameResultDto> totalResult = frames.stream()
				.map(Frame::getFrameResult)
				.collect(Collectors.toList());
		return new PlayerResultDto(name, totalResult);
	}

	public boolean isFrameOver(int frameNumber) {
		if (frameNumber > frames.size()) {
			return false;
		}
		Frame frame = frames.get(frameNumber - 1);
		return frame.isCompleted();
	}
}
