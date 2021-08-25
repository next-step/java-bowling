package bowling.model.frame;

public class FinalFrame extends Frame {

	private static final String FRAME_rANGE_ERROR_MESSAGE = "마지막 프레임은 10만 가능 합니다.";
	private static final int FINAL_FRAME_NUMBER = 10;
	private static final int MAX_PLAY_COUNT = 2;
	private static final int MIN_PLAY_COUNT = 1;

	public FinalFrame(int frameNumber) {
		super(frameNumber);
	}

	@Override
	void checkFrameNumber(int frameNumber) {
		if (frameNumber != FINAL_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_rANGE_ERROR_MESSAGE);
		}
	}

	@Override
	boolean isGameEnd() {
		return (playCount > MIN_PLAY_COUNT && bowlGame.isNotStrikeOrSpare()) || playCount > MAX_PLAY_COUNT;
	}
}
