package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public final class Score {
	private static Map<Integer, Score> CACHE = new HashMap<>();
	public static final Score ZERO = Score.of(0);
	public static final Score TEN = Score.of(10);

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score of(int scoreValue) {
		checkArgument(scoreValue <= 10);
		checkArgument(scoreValue >= 0);
		Score cacheScore = CACHE.get(scoreValue);
		if (Objects.nonNull(cacheScore)) {
			return cacheScore;
		}
		cacheScore = new Score(scoreValue);
		CACHE.put(scoreValue, cacheScore);
		return cacheScore;
	}

	public Score add(Score score) {
		return Score.of(toInteger() + score.toInteger());
	}

	public boolean loe(Score score) {
		return this.toInteger() <= score.toInteger();
	}

	public boolean isZero() {
		return this.equals(Score.ZERO);
	}

	public boolean isTen() {
		return this.equals(Score.TEN);
	}

	public int toInteger() {
		return this.score;
	}

	@Override
	public String toString() {
		return String.valueOf(score);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Score score1 = (Score) o;

		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return score;
	}
}
