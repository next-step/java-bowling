package bowling.model.frame;

public class NormalFrame extends Frame {

	private static final String FRAME_rANGE_ERROR_MESSAGE = "일반 프레임은 1~9 까지 입니다.";
	private static final int MAX_FRAME_NUMBER = 9;
	private static final int MIN_FRAME_NUMBER = 1;
	private static final int MAX_PLAY_COUNT = 1;
	private static final int ZERO_POINT = 0;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
	}

	@Override
	void checkFrameNumber(int frameNumber) {
		if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
			throw new IllegalArgumentException(FRAME_rANGE_ERROR_MESSAGE);
		}
	}

	@Override
	boolean isGameEnd() {
		return (playCount > ZERO_POINT && playBowl.isStrike()) || playCount > MAX_PLAY_COUNT;
	}

}
