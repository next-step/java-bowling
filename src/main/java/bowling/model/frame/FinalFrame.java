package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.play.Playable;

public class FinalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "마지막 프레임은 10만 가능 합니다.";
	private static final int FINAL_FRAME_NUMBER = 10;

	public FinalFrame(int frameNumber) {
		super(frameNumber);
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber != FINAL_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_RANGE_ERROR_MESSAGE);
		}
	}

	@Override
	public void playGame(int strikeNumber) {
		play.play(new Pin(strikeNumber));
	}

	@Override
	public String getGameStatus() {
		return play.getGameStatus();
	}

	@Override
	public boolean isGameEnd() {
		return play.isGameEnd();
	}

	@Override
	int calculateScore(Playable beforeResult) {
		return play.calculateFrame(beforeResult);
	}

	@Override
	int calculateScoreDouble(Playable beforeResult) {
		return play.calculateDouble(beforeResult);
	}

	@Override
	public int getGameScore() {
		if (!isGameEnd()) {
			return -1;
		}
		return play.getTotalScore();
	}
}
