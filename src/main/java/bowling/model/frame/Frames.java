package bowling.model.frame;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

	private static final int FIRST_FRAME_NUMBER = 1;
	private static final int FINAL_FRAME = 10;
	private final List<Frame> frames;
	private int presentFrame;

	public Frames(List<Frame> frames) {
		this.frames = frames;
		presentFrame = FIRST_FRAME_NUMBER;
	}

	public void playBowling(int strikeNumber) {
		if (presentFrame < FINAL_FRAME && frames.get(presentFrame - 1).isGameEnd()) {
			presentFrame = presentFrame + 1;
		}
		frames.get(presentFrame - 1).playGame(strikeNumber);
	}

	public List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}

	public int getPresentFrame() {
		return presentFrame;
	}

	public boolean isContinueGame() {
		return (presentFrame == FINAL_FRAME) && findEndFrame();
	}

	private boolean findEndFrame() {
		return frames.stream()
			.allMatch(Frame::isGameEnd);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frames frames1 = (Frames)o;
		return presentFrame == frames1.presentFrame && Objects.equals(frames, frames1.frames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frames, presentFrame);
	}
}
