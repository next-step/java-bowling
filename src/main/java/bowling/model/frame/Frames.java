package bowling.model.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.Comparator.FrameComparator;
import bowling.model.ScoreCalculator;

public class Frames {

	private static final int FIRST_FRAME_NUMBER = 1;
	private static final int FINAL_FRAME = 10;
	private static final int NORMAL_FIRST_FRAME = 1;
	private static final int NORMAL_FINAL_FRAME = 9;
	private static final int FRAME_INDEX_STEP = 1;

	private final List<Frame> frames;
	private int presentFrame;

	private Frames(List<Frame> frames) {
		this.frames = frames;
		presentFrame = FIRST_FRAME_NUMBER;
	}

	public static Frames initCreateFrames(List<Frame> frames) {
		return new Frames(frames);
	}

	public static Frames initCreateFrames() {
		List<Frame> frames = new ArrayList<>();
		Frame finalFrame = createFinalFrame(frames);
		return new Frames(createNormalFrame(frames, finalFrame));
	}

	private static List<Frame> createNormalFrame(List<Frame> frames, Frame finalFrame) {
		Frame previousFrame = finalFrame;
		for (int i = NORMAL_FINAL_FRAME; i >= NORMAL_FIRST_FRAME; i--) {
			NormalFrame normalFrame = new NormalFrame(i, previousFrame);
			frames.add(normalFrame);
			previousFrame = normalFrame;
		}

		frames.sort(new FrameComparator());
		return frames;
	}

	private static Frame createFinalFrame(List<Frame> frames) {
		FinalFrame finalFrame = new FinalFrame(FINAL_FRAME);
		frames.add(finalFrame);
		return finalFrame;
	}

	public void playBowling(int strikeNumber) {
		Frame frame = frames.get(presentFrame - FRAME_INDEX_STEP);
		if (!frame.isGameEnd()) {
			frame.playGame(strikeNumber);
		}
		if (presentFrame < FINAL_FRAME && frame.isGameEnd()) {
			presentFrame = presentFrame + FRAME_INDEX_STEP;
		}
	}

	public List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}

	public int getPresentFrame() {
		return presentFrame;
	}

	public boolean isContinueGame() {
		if (presentFrame < FINAL_FRAME) {
			return true;
		}
		return findEndFrame();
	}

	private boolean findEndFrame() {
		return frames.stream()
			.filter(frame -> frame.getFrameNumber() == FINAL_FRAME)
			.noneMatch(Frame::isGameEnd);
	}

	public ScoreCalculator getScoreBoard() {
		return ScoreCalculator.createScore(frames);
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
