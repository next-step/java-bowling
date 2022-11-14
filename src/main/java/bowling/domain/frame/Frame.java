package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.PitchType;
import bowling.exception.BadRequestException;

public class Frame {

	private static final int STRIKE_SCORE = 10;
	private final List<Integer> scores = new ArrayList<>();

	public Frame() {}

	public void add(Integer score) {
		validateScore(score);
		scores.add(score);
	}

	public List<Integer> getScores() {
		return scores;
	}

	public Integer get(int index) {
		return scores.get(index);
	}

	public Integer size() {
		return scores.size();
	}

	public Integer getFirstIndex() {
		return 0;
	}

	public Integer getLastIndex() {
		return scores.size() - 1;
	}

	public Integer sumInRange(int start, int end) {
		return IntStream.range(start, end)
			.map(scores::get)
			.sum() % 10;
	}

	public Boolean isStrike(int index) {
		return get(index) == STRIKE_SCORE;
	}

	public Boolean isSpare(int index) {
		return sumInRange(0, index) + get(index) == 10;
	}

	public Boolean isMiss(int index) {
		return scores.get(index) > 0 && sumInRange(0, index) + get(index) < 10;
	}

	public Boolean isGutter(int index) {
		return scores.get(index) == 0;
	}

	public PitchType getPitchType(int index) {

		if (isStrike(index)) {
			return PitchType.STRIKE;
		}

		if (isSpare(index)) {
			return PitchType.SPARE;
		}

		if (isMiss(index)) {
			return PitchType.MISS;
		}

		if (isGutter(index)) {
			return PitchType.GUTTER;
		}
		return null;
	}

	private void validateScore(int score) {
		if (score > 10) {
			throw new BadRequestException("유효한 점수가 아닙니다.");
		}
	}
}
