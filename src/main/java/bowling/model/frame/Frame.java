package bowling.model.frame;

import java.util.Objects;

import bowling.model.play.Play;
import bowling.model.play.Playable;

public abstract class Frame {

	protected final Playable play;
	protected final int frameNumber;

	public Frame(int frameNumber) {
		checkFrameNumber(frameNumber);
		this.play = new Play(frameNumber);
		this.frameNumber = frameNumber;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	abstract void playGame(int strikeNumber);

	abstract void checkFrameNumber(int frameNumber);

	public abstract String getGameStatus();

	public abstract boolean isGameEnd();

	abstract int calculateScore(Playable playResult);

	abstract int calculateScoreDouble(Playable beforeResult);

	public abstract int getGameScore();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frame frame = (Frame)o;
		return frameNumber == frame.frameNumber && Objects.equals(play, frame.play);
	}

	@Override
	public int hashCode() {
		return Objects.hash(play, frameNumber);
	}
}
