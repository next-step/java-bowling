package bowling.util;

import java.util.Arrays;
import java.util.List;

import bowling.domain.result.Gutter;
import bowling.domain.result.Miss;
import bowling.domain.result.Result;
import bowling.domain.result.Spare;
import bowling.domain.result.Strike;
import bowling.domain.score.Score;

public class ResultUtil {

	private static Strike STRIKE = new Strike();
	private static Spare SPARE = new Spare();
	public static final List<Result> BONUS_SCORE_RESULT = Arrays.asList(STRIKE, SPARE);
	private static Miss MISS = new Miss();
	private static Gutter GUTTER = new Gutter();
	private static List<Result> results = Arrays.asList(STRIKE, SPARE, MISS, GUTTER);

	public static Result findByScores(Score first, Score second) {
		return results.stream()
			.filter(result -> result.isMatch(first, second))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("not in score expected possibilities."));
	}
}
