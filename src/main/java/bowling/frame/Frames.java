package bowling.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

	private static final int MAX_FRAME_NUMBER = 10;

	private final List<Frame> values = new ArrayList<>();

	private Frames(List<Frame> frames) {
		this.values.addAll(frames);
	}

	public static Frames start() {
		return new Frames(List.of(new GeneralFrame()));
	}

	public boolean isEnd() {
		return values.stream()
			.allMatch(Frame::isEnd);
	}

	public void throwBowl(int throwCount) {
		Frame lastFrame = lastFrame();
		lastFrame.throwBowl(throwCount);
		if (lastFrame.isEnd() && frameNumber() < MAX_FRAME_NUMBER) {
			values.add(nextFrame());
		}
	}

	private Frame nextFrame() {
		if (frameNumber() == MAX_FRAME_NUMBER) {
			return new EndFrame();
		}
		return new GeneralFrame();
	}

	private Frame lastFrame() {
		return values.get(frameNumber() - 1);
	}

	public int frameNumber() {
		return values.size();
	}

}
