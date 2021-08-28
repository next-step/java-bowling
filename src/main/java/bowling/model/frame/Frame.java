package bowling.model.frame;

import java.util.ArrayList;
import java.util.Objects;

import bowling.model.PlayResult;

public abstract class Frame {

	protected PlayResult playResult;
	protected final int frameNumber;
	protected Frame nextFrame;

	public Frame(int frameNumber) {
		checkFrameNumber(frameNumber);
		this.playResult = new PlayResult(new ArrayList<>());
		this.frameNumber = frameNumber;
	}

	public void bringNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	abstract void playGame(int strikeNumber);

	abstract void checkFrameNumber(int frameNumber);

	public abstract String getGameStatus();

	abstract boolean isGameEnd();

	abstract int getStrikeAndSpareNextScore(boolean strike, boolean spare, int sumScore);

	public abstract int getGameScore();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frame frame = (Frame)o;
		return frameNumber == frame.frameNumber && Objects.equals(playResult, frame.playResult)
			&& Objects.equals(nextFrame, frame.nextFrame);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playResult, frameNumber, nextFrame);
	}
}
