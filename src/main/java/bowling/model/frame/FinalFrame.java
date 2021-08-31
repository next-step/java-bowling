package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.PlayResult;
import bowling.model.play.Play;
import bowling.model.play.Playable;

public class FinalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "마지막 프레임은 10만 가능 합니다.";
	private static final int FINAL_FRAME_NUMBER = 10;

	private final Playable play;

	public FinalFrame(int frameNumber) {
		super(frameNumber);
		play = new Play(frameNumber);
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber != FINAL_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_RANGE_ERROR_MESSAGE);
		}
	}

	@Override
	public void playGame(int strikeNumber) {
		Play play = this.play.play(new Pin(strikeNumber));
		playResult = new PlayResult(play.getGameResult());
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
	int calculateScore(PlayResult beforeResult) {
		return playResult.calculateFrame(beforeResult);
	}

	@Override
	int calculateScoreDouble(PlayResult beforeResult) {
		return playResult.calculateDouble(beforeResult);
	}

	@Override
	public int getGameScore() {
		if (!isGameEnd()) {
			return -1;
		}
		return playResult.getTotalScore();
	}
}
