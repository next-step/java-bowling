package domain.frame.result;

import domain.Score;
import domain.frame.FinalFrame;
import domain.frame.Frame;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hspark on 22/11/2018.
 */
public interface FrameResult {
	int MAX_FRAME = 10;
	List<Class<? extends FrameResult>> FINAL_AVAILABLE_RESULT = Arrays.asList(Spare.class, Strike.class);

	default Frame next() {
		if (getFrameNumber() == MAX_FRAME && FINAL_AVAILABLE_RESULT.contains(self().getClass())) {
			return new FinalFrame();
		}
		return nextGeneralFrame();
	}

	Frame nextGeneralFrame();

	Score getScore();

	int getFrameNumber();

	int getNextNumber();

	default boolean hasNext() {
		return getFrameNumber() <= MAX_FRAME;
	}

	FrameResult self();
}
