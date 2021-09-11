package bowling.domain;

import java.util.Objects;

public class Score {

	public static final int UNSCORED = -1;

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

	public static Score ofUnscored() {
		return from(UNSCORED);
	}

	public Score add(Score score) {
		if (score.value == UNSCORED) {
			return from(UNSCORED);
		}
		return from(value + score.value);
	}

	public boolean isUnscored() {
		return value == UNSCORED;
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
