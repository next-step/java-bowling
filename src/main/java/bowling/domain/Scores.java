package bowling.domain;

import java.util.Objects;

public class Scores {

	private final Score first;
	private Score second;

	private Scores(Score first) {
		this.first = first;
	}

	public static Scores from(Score first) {
		return new Scores(first);
	}

	public void addSecondScore(Score second) {
		first.add(second);
		this.second = second;
	}

	public Score getFirst() {
		return first;
	}

	public Score getSecond() {
		return second;
	}

	public Result checkResult() {
		if (Objects.isNull(second)) {
			throw new IllegalStateException("scores does not have second score result!");
		}
		return Result.STRIKE;
	}
}
