package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import bowling.exception.UserException;
import bowling.views.BowlingScoreString;

public class NormalFrame implements Frame {

	private final FirstWard firstWard;
	private final SecondWard secondWard;

	public NormalFrame(FirstWard firstWard) {
		this(firstWard, null);
	}

	public NormalFrame(FirstWard firstWard, SecondWard secondWard) {
		this.firstWard = firstWard;
		this.secondWard = secondWard;
	}

	@Override
	public boolean isKeepGoing() {
		return firstWard.hasNext();
	}

	@Override
	public String getScoreString() {
		return BowlingScoreString.getScoreString(getBowlingScoreType(), firstWard, secondWard);
	}

	@Override
	public boolean isLast() {
		return !isKeepGoing() || secondWard != null;
	}

	private static NormalFrame ofFirst(int score) {
		return new NormalFrame(new FirstWard(score));
	}

	private static NormalFrame ofSecond(int score, Frame frame) {
		validate(score + frame.getWards().get(0).getScore());
		return new NormalFrame((FirstWard) frame.getWards().get(0), new SecondWard(score));
	}

	public static NormalFrame ofNext(int score, Frame frame) {
		if (frame == null) {
			return ofFirst(score);
		}
		return ofSecond(score, frame);
	}

	private static void validate(int score) {
		if (score < 0 || score > 10) {
			throw new UserException(BOWLING_BOUND);
		}
	}

	public BowlingScoreType getBowlingScoreType() {
		return BowlingScoreType.getInstance(firstWard, secondWard);
	}

	@Override
	public int getScore() {
		return getFirstScore() + getSecondScore();
	}

	@Override
	public int getRemainderCount() {
		return getBowlingScoreType().getRemainderCount();
	}

	private int getFirstScore() {
		return Optional.ofNullable(firstWard)
			.orElseGet(() -> new FirstWard(0))
			.getScore();
	}

	private int getSecondScore() {
		return Optional.ofNullable(secondWard)
			.orElseGet(() -> new SecondWard(0))
			.getScore();
	}

	public List<Ward> getWards() {
		return Arrays.asList(firstWard, secondWard);
	}
}
