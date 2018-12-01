package bowlinggame.domain.frame;

import bowlinggame.domain.frame.result.Result;
import bowlinggame.domain.frame.result.Results;
import bowlinggame.domain.frame.result.Score;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {

	public static final int MAX_ROLL_OPPORTUNITY = 2;

	private FrameNumber frameNumber;
	private Results results;
	private Frame nextFrame;

	public NormalFrame(FrameNumber frameNumber) {
		this(frameNumber, new Results());
	}

	public NormalFrame(FrameNumber frameNumber, Results results) {
		this.frameNumber = frameNumber;
		this.results = results;
	}

	@Override
	public Frame next() {
		if (nextFrame == null) {
			nextFrame = Frame.of(frameNumber.next());
		}
		return nextFrame;
	}

	@Override
	public Frame roll(int pinCount) {
		if (isCompleted()) {
			return next().roll(pinCount);
		}

		Pin pin = Pin.fromKnockedPinCount(results.getTotalKnockedDownPinCount());
		results.record(pin.knockDown(pinCount));
		return this;
	}

	@Override
	public Score getScore() {
		Score score = initScore();
		if (score.hasBonus()) {
			return next().calculateBonus(score);
		}
		return score;
	}

	private Score initScore() {
		Optional<Result> result = results.getCurrentResult();
		if (result.isPresent()) {
			return Score.of(results.getTotalKnockedDownPinCount(),
					result.get().getBonusCount());
		}
		return Score.init();
	}

	@Override
	public Score calculateBonus(Score score) {
		Score calculatedScore = results.addScore(score);
		if (calculatedScore.hasBonus()) {
			return next().calculateBonus(calculatedScore);
		}
		return score;
	}

	@Override
	public boolean isCompleted() {
		return results.isSameRollOpportunity(MAX_ROLL_OPPORTUNITY)
				|| results.isAllKnockedDown();
	}

	@Override
	public boolean isSameNumber(FrameNumber frameNumber) {
		return this.frameNumber == frameNumber;
	}

	@Override
	public FrameResult getFrameResult() {
		if (isCompleted()) {
			return new FrameResult(results.getDisplayResults(), getScore());
		}
		return new FrameResult(results.getDisplayResults());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof NormalFrame)) {
			return false;
		}
		NormalFrame that = (NormalFrame) o;
		return Objects.equals(frameNumber, that.frameNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber);
	}
}
