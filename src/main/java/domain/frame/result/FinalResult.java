package domain.frame.result;

import domain.Score;
import domain.frame.Frame;

/**
 * Created by hspark on 22/11/2018.
 */
public class FinalResult implements FrameResult {
	private Score score;

	public FinalResult(Score score) {
		this.score = score;
	}

	@Override
	public Frame nextGeneralFrame() {
		throw new IllegalArgumentException("마지막 프레임이므로 생성이 불가능합니다.");
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public int getFrameNumber() {
		return FrameResult.MAX_FRAME;
	}

	@Override
	public int getNextNumber() {
		return FrameResult.MAX_FRAME;
	}

	@Override
	public FrameResult self() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public String toString() {
		if (score.isTen()) {
			return Strike.STRIKE_STR;
		}
		if (score.isZero()) {
			return Gutter.GUTTER_STR;
		}
		return score.toString();
	}
}
