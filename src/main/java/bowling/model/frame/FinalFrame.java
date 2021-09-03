package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.score.Score;

public class FinalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "마지막 프레임은 10만 가능 합니다.";
	private static final int FINAL_FRAME_NUMBER = 10;

	private Score score;

	public FinalFrame(int frameNumber) {
		super(frameNumber);
		score = Score.init();
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber != FINAL_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_RANGE_ERROR_MESSAGE);
		}
	}

	@Override
	public void playGame(int strikeNumber) {
		score = play.play(new Pin(strikeNumber));
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
	int calculateScore(Score score) {
		return this.score.calculate(score);
	}

	@Override
	public int getGameScore() {
		if (!isGameEnd()) {
			return -1;
		}
		return score.getScore();
	}
}
