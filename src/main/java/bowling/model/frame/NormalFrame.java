package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.PlayResult;
import bowling.model.play.NormalPlay;

public class NormalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "일반 프레임은 1~9 까지 입니다.";
	private static final int MAX_FRAME_NUMBER = 9;
	private static final int MIN_FRAME_NUMBER = 1;

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
	public String getGameScore() {
		return playResult.getGameScore();
	}

	@Override
	public boolean isGameEnd() {
		return normalPlay.isGameEnd();
	}

}
