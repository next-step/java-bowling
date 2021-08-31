package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.play.Playable;

public class NormalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "일반 프레임은 1~9 까지 입니다.";
	private static final int MAX_FRAME_NUMBER = 9;
	private static final int MIN_FRAME_NUMBER = 1;

	private final Frame nextFrame;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
		this.nextFrame = getNextFrame(frameNumber);
	}

	private Frame getNextFrame(int frameNumber) {
		if (frameNumber == 9) {
			return new FinalFrame(10);
		}
		return new NormalFrame(frameNumber + 1);
	}

	public NormalFrame(int frameNumber, Frame nextFrame) {
		super(frameNumber);
		this.nextFrame = nextFrame;
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
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
		if (play.isStrike() && beforeResult.isStrike()) {
			return nextFrame.calculateScoreDouble(beforeResult);
		}
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
		if (play.isMiss()) {
			return play.getTotalScore();
		}
		return nextFrame.calculateScore(play);
	}
}
