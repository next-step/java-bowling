package domain;

import domain.frame.Frame;
import domain.frame.result.FrameResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hspark on 22/11/2018.
 */
public class Bowling {
	public static final int FIRST_FRAME_NUMBER = 1;
	private List<FrameResult> frameResults = new ArrayList<>();

	public void bowlFirst(Score score) {
		frameResults.clear();
		Frame frame = Frame.first();
		bowl(score, frame);
	}

	public void bowl(Score score) {
		if (frameResults.isEmpty()) {
			bowlFirst(score);
			return;
		}
		Frame frame = getLastFrameResult().next();
		bowl(score, frame);
	}

	private void bowl(Score score, Frame frame) {
		FrameResult result = frame.pitch(score);
		frameResults.add(result);
	}

	public boolean hasNext() {
		return frameResults.isEmpty() || getLastFrameResult().hasNext();
	}

	public FrameResult getLastFrameResult() {
		int lastIndex = frameResults.size() - 1;
		return frameResults.get(lastIndex);
	}

	public int getNextFrameNumber(){
		return getLastFrameResult().getNextNumber();
	}

	public BowlingScoreBoard getScoreBoard() {
		return new BowlingScoreBoard(frameResults);
	}
}
