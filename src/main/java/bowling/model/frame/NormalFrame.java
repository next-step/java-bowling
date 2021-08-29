package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.PlayResult;
import bowling.model.play.NormalPlay;

public class NormalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "일반 프레임은 1~9 까지 입니다.";
	private static final int MAX_FRAME_NUMBER = 9;
	private static final int MIN_FRAME_NUMBER = 1;
	private static final int CUSTOM_SECTION_POINT = -1;
	private static final int FIRST_INDEX = 0;

	private final NormalPlay normalPlay;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
		normalPlay = new NormalPlay();
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_RANGE_ERROR_MESSAGE);
		}
	}

	@Override
	public void playGame(int strikeNumber) {
		playResult = new PlayResult(normalPlay.play(new Pin(strikeNumber)));
	}

	@Override
	public String getGameStatus() {
		return playResult.getGameScore();
	}

	@Override
	public boolean isGameEnd() {
		return normalPlay.isGameEnd();
	}

	@Override
	int getStrikeAndSpareNextScore(boolean strike, boolean spare, int sumScore) {
		if (spare && playResult.isGameStart()) {
			return sumScore + playResult.findScore(FIRST_INDEX);
		}
		if (strike && playResult.isStrike()) {
			return nextFrame.getStrikeAndSpareNextScore(false, true, sumScore + playResult.findTotalScore());
		}
		if (strike && playResult.isSecondPlay()) {
			return sumScore + playResult.findTotalScore();
		}
		return CUSTOM_SECTION_POINT;
	}

	@Override
	public int getGameScore() {
		if (!isGameEnd()) {
			return CUSTOM_SECTION_POINT;
		}
		if (!(playResult.isStrike() || playResult.isSpare())) {
			return playResult.findTotalScore();
		}
		return nextFrame.getStrikeAndSpareNextScore(playResult.isStrike(), playResult.isSpare(), 10);
	}
}
