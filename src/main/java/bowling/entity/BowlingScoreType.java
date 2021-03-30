package bowling.entity;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum BowlingScoreType {
	STRIKE("X", (first, second) -> first.getScore() == 10),
	SPARE("/", (first, second) -> first.getScore() + second.getScore() == 10),
	MISS("-", (first, second) -> first.getScore() != 0 && second.getScore() == 0),
	GUTTER("-", (first, second) -> first.getScore() == 0 && second.getScore() == 0),
	OTHER("", (first, second) -> true);

	private final String score;
	private final BiFunction<Ward, Ward, Boolean> checkFunc;

	BowlingScoreType(String score, BiFunction<Ward, Ward, Boolean> checkFunc) {
		this.score = score;
		this.checkFunc = checkFunc;
	}

	public static BowlingScoreType getInstance(Ward first, Ward second) {
		try {
			return Arrays.stream(values())
				.filter(type -> type.checkFunc.apply(first, second))
				.findFirst()
				.orElseGet(() -> OTHER);
		} catch (NullPointerException e) {
			return OTHER;
		}
	}

	public String getScore() {
		return score;
	}
}
