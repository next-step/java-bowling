package bowling.view;

import bowling.domain.result.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class ViewResult {

	public static String printScores(Scores scores) {
		Score first = scores.getFirst().orElseThrow(() -> new IllegalArgumentException("not found"));
		if (first.getScore() == 10 && scores.isSecondScoreNull()) {
			return String.format("   %s    ", first.getScore());
		}

		Result result = scores.checkResult();
		return result.renderString(scores);
	}
}
