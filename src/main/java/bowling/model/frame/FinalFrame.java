package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.PlayResult;
import bowling.model.play.FinalPlay;
import bowling.model.play.Playable;

public class FinalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "마지막 프레임은 10만 가능 합니다.";
	private static final int FINAL_FRAME_NUMBER = 10;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;

	private final Playable finalPlay;

	public FinalFrame(int frameNumber) {
		super(frameNumber);
		finalPlay = new FinalPlay();
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber != FINAL_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_RANGE_ERROR_MESSAGE);
		}
	}

	@Override
	public void playGame(int strikeNumber) {
		playResult = new PlayResult(finalPlay.play(new Pin(strikeNumber)));
	}

	@Override
	public String getGameStatus() {
		return playResult.getGameScore();
	}

	@Override
	public boolean isGameEnd() {
		return finalPlay.isGameEnd();
	}

	@Override
	int getStrikeAndSpareNextScore(int leftStep, int sumScore) {
		if (leftStep == 1 && playResult.isGameStart()) {
			return sumScore + playResult.findScore(FIRST_INDEX);
		}
		if (leftStep == 2 && playResult.isSecondPlay()) {
			return sumScore + (playResult.findScore(FIRST_INDEX) + playResult.findScore(SECOND_INDEX));
		}
		return -1;
	}

	@Override
	public int getGameScore() {
		if (!isGameEnd()) {
			return -1;
		}
		return playResult.findTotalScore();
	}
}
