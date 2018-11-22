package domain.frame;

import domain.Score;
import domain.frame.result.FrameResult;
import domain.frame.result.Gutter;
import domain.frame.result.Hit;
import domain.frame.result.Strike;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public class FirstBowlFrame implements Frame {
	private static final int FIRST_NORMAL_FRAME_NUMBER = 1;

	private int frameNumber;

	public FirstBowlFrame(int frameNumber) {
		checkArgument(frameNumber >= FIRST_NORMAL_FRAME_NUMBER);
		this.frameNumber = frameNumber;
	}

	public FrameResult pitch(Score score) {
		if (score.isTen()) {
			return new Strike(frameNumber);
		}
		if(score.isZero()){
			return new Gutter(frameNumber, true);
		}
		return new Hit(frameNumber, score);
	}

	public int getFrameNumber() {
		return frameNumber;
	}
}
