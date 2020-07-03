package bowling.view;

import java.util.Optional;

import bowling.domain.result.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class ViewResult {

	private final Result result;

	ViewResult(Result result) {
		this.result = result;
	}

	public static String printScores(Scores scores) {
		Optional<Score> first = Optional.ofNullable(scores.getFirst()).orElseThrow(() ->
			new IllegalArgumentException("not found"));
		Optional<Score> second = Optional.ofNullable(scores.getSecond()).orElseThrow(() ->
			new IllegalArgumentException("not found"));

		if (! first.isPresent()) {
			return "      ";
		}
		if (! (first.map(Score::getScore).get() == 10) && ! second.isPresent()) {
			return String.format("   %s    ", first.get().getScore());
		}
		Result result = scores.checkResult();
		return result.renderString(scores);
	}
}
