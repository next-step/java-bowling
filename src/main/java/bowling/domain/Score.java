package bowling.domain;

import java.util.Objects;

public class Score {

	public static final int NOT_SCORED = -1;

	private final int value;

	private Score(int value) {
		this.value = value;
	}

	public static Score from(int value) {
		return new Score(value);
	}

	public static Score of(PitchResult result) {
		return from(result.fallenPins());
	}

	public static Score of(Frame frame) {
		Score score = Score.ofZero();
		frame.results().stream()
			.map(Score::of)
			.forEach(score::add);
		return score;
	}

	public static Score ofZero() {
		return from(0);
	}

	public static Score ofNotScored() {
		return from(NOT_SCORED);
	}

	public Score add(Score score) {
		if (score.value == NOT_SCORED) {
			return from(NOT_SCORED);
		}
		return from(value + score.value);
	}

	public boolean isNotScored() {
		return value == NOT_SCORED;
	}

	public int value() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score = (Score)o;
		return value == score.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
