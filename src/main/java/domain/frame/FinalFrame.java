package domain.frame;

import domain.Score;
import domain.frame.result.FinalResult;
import domain.frame.result.FrameResult;

/**
 * Created by hspark on 22/11/2018.
 */
public class FinalFrame implements Frame {
	@Override
	public FrameResult pitch(Score score) {
		return new FinalResult(score);
	}

	@Override
	public int getFrameNumber() {
		return FrameResult.MAX_FRAME;
	}
}
