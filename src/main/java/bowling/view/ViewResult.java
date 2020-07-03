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
	public static String printScores(Scores scores) {
		Score first = scores.getFirst().orElseThrow(() ->
			new IllegalArgumentException("not found"));

		if (first.getScore() == 10 && scores.isSecondScoreNull()) {
			return String.format("   %s    ", first.getScore());
		}

		Result result = scores.checkResult();
		return result.renderString(scores);
	}
}
