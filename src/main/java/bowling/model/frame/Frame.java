package bowling.model.frame;

import java.util.Objects;

import bowling.model.PlayBowl;
import bowling.model.PlayResult;
import bowling.model.Pin;

public abstract class Frame {

	private static final String END_GAME_ERROR_MESSAGE = "프레임별 플레이 횟수가 초과 되었습니다.";
	protected int playCount;
	protected final PlayBowl playBowl;
	private final int frameNumber;

	public Frame(int frameNumber) {
		checkFrameNumber(frameNumber);
		this.frameNumber = frameNumber;
		playBowl = new PlayBowl(frameNumber);
	}

	public PlayResult playGame(int strikeNumber) {
		checkEndGame();
		playCount++;
		return playBowl.play(new Pin(strikeNumber));
	}

	public String getGameScore() {
		return playBowl.getGameScore();
	}

	private void checkEndGame() {
		if (isGameEnd()) {
			throw new IllegalStateException(END_GAME_ERROR_MESSAGE);
		}
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	abstract void checkFrameNumber(int frameNumber);

	abstract boolean isGameEnd();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frame frame = (Frame)o;
		return playCount == frame.playCount && Objects.equals(playBowl, frame.playBowl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playCount, playBowl);
	}

}
