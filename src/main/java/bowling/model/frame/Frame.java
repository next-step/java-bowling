package bowling.model.frame;

import java.util.Objects;

import bowling.model.BowlGame;
import bowling.model.GameResult;
import bowling.model.Pin;

public abstract class Frame {

	private static final String END_GAME_ERROR_MESSAGE = "프레임별 플레이 횟수가 초과 되었습니다.";
	protected int playCount;
	protected final BowlGame bowlGame;

	public Frame(int frameNumber) {
		checkFrameNumber(frameNumber);
		bowlGame = new BowlGame(frameNumber);
	}

	public GameResult playGame(int strikeNumber) {
		checkEndGame();
		playCount++;
		return bowlGame.play(new Pin(strikeNumber));
	}

	private void checkEndGame() {
		if (isGameEnd()) {
			throw new IllegalStateException(END_GAME_ERROR_MESSAGE);
		}
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
		return playCount == frame.playCount && Objects.equals(bowlGame, frame.bowlGame);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playCount, bowlGame);
	}
}
