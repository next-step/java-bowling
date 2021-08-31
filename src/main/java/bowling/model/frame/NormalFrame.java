package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.PlayResult;
import bowling.model.play.Play;
import bowling.model.play.Playable;

public class NormalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "일반 프레임은 1~9 까지 입니다.";
	private static final int MAX_FRAME_NUMBER = 9;
	private static final int MIN_FRAME_NUMBER = 1;

	private final Playable play;
	private final Frame nextFrame;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
		this.nextFrame = getNextFrame(frameNumber);
		play = new Play(frameNumber);
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
		play = new Play(frameNumber);
	}

	@Override
	public void checkFrameNumber(int frameNumber) {
		if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
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
		if (playResult.isStrike() && beforeResult.isStrike()) {
			return nextFrame.calculateScoreDouble(beforeResult);
		}
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
		if (play.isMiss()) {
			return playResult.getTotalScore();
		}
		return nextFrame.calculateScore(playResult);
	}
}
