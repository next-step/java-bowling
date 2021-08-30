package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.PlayResult;
import bowling.model.play.NormalPlay;
import bowling.model.play.Playable;

public class NormalFrame extends Frame {

	private static final String FRAME_RANGE_ERROR_MESSAGE = "일반 프레임은 1~9 까지 입니다.";
	private static final int MAX_FRAME_NUMBER = 9;
	private static final int MIN_FRAME_NUMBER = 1;
	private static final int FIRST_INDEX = 0;

	private final Playable normalPlay;
	private final Frame nextFrame;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
		this.nextFrame = getNextFrame(frameNumber);
		normalPlay = new NormalPlay();
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
	int getStrikeAndSpareNextScore(int leftStep, int sumScore) {
		if (leftStep == 1 && playResult.isGameStart()) {
			return sumScore + playResult.findScore(FIRST_INDEX);
		}
		if (leftStep == 2 && playResult.isStrike()) {
			return nextFrame.getStrikeAndSpareNextScore(1, sumScore + playResult.findTotalScore());
		}
		if (leftStep == 2 && playResult.isSecondPlay()) {
			return sumScore + playResult.findTotalScore();
		}
		return -1;
	}

	@Override
	public int getGameScore() {
		if (!isGameEnd()) {
			return -1;
		}
		if (!(playResult.isStrike() || playResult.isSpare())) {
			return playResult.findTotalScore();
		}
		return nextFrame.getStrikeAndSpareNextScore(playResult.findScoreNextStep(), 10);
	}
}
