package domain.frame;

import domain.Score;
import domain.frame.result.FrameResult;
import domain.frame.result.Gutter;
import domain.frame.result.Miss;
import domain.frame.result.Spare;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public class SecondBowlFrame implements Frame {
	private static final int FIRST_NORMAL_FRAME_NUMBER = 1;
	private static final int LAST_NORMAL_FRAME_NUMBER = 9;

	private int frameNumber;
	private Score firstScore;

	public SecondBowlFrame(int frameNumber, Score firstScore) {
		checkArgument(frameNumber >= FIRST_NORMAL_FRAME_NUMBER);
		this.frameNumber = frameNumber;
		this.firstScore = firstScore;

	}

	@Override
	public FrameResult pitch(Score score) {
		Score sumScore = firstScore.add(score);
		checkArgument(sumScore.loe(Score.TEN));
		if (sumScore.isTen()) {
			return new Spare(getFrameNumber(), score);
		}
		if (sumScore.isZero()) {
			return new Gutter(getFrameNumber(), false);
		}
		return new Miss(getFrameNumber(), score);
	}

	@Override
	public int getFrameNumber() {
		return frameNumber;
	}
}
