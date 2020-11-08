package bowling.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ScoreSymbol {
	STRIKE("X", (score, isFirstTurn) -> score == 10 && isFirstTurn),
	SPARE("/", (score, isFirstTurn) -> score == 10 && !isFirstTurn),
	GUTTER("-", (score, isFirstTurn) -> score == 0 && !isFirstTurn),
	MISS("", (score, isFirstTurn) -> true);

	private String value;
	private BiPredicate<Integer, Boolean> expression;

	ScoreSymbol(String value, BiPredicate<Integer, Boolean> expression) {
		this.value = value;
		this.expression = expression;
	}

	public static ScoreSymbol valueOf(int score, boolean isFirstTurn) {
		return Arrays.stream(values())
			.filter(it -> it.expression.test(score, isFirstTurn))
			.findFirst()
			.orElse(MISS);
	}
}
