package domain.frame;

import domain.Score;
import domain.frame.result.FrameResult;

/**
 * Created by hspark on 21/11/2018.
 */
public interface Frame {
	FrameResult pitch(Score score);
	int getFrameNumber();

	static Frame first() {
		return new FirstBowlFrame(1);
	}
}
