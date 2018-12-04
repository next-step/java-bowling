package domain.frame;

import domain.Pin;
import domain.Score;
import domain.frame.state.State;

import java.util.List;

/**
 * Created by hspark on 21/11/2018.
 */
public interface Frame {
	int MAX_FRAME = 10;
	int FIRST_FRAME = 1;

	static Frame first() {
		return new NormalFrame(Frame.FIRST_FRAME);
	}

	Frame pitch(Pin pin);

	State getState();

	boolean isFinished();

	Frame self();

	int getFrameNumber();

	void setNextFrame(Frame frame);

	Frame getNextFrame();

	int getScore();

	Frame getLastFrame();

	/**
	 * 마지막 프레임이고, 프레임이 끝났으면 다음이 없다.
	 * @return
	 */
	boolean isLeftFrame();

	boolean hasNext();

	FrameResults getFrameResults();

	List<FrameResult> makeFrameResults(List<FrameResult> frameResults);

	Score calculateScore(Score previousScore);
}
