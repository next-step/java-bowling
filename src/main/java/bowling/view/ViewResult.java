package bowling.view;

import java.util.Optional;

import bowling.domain.result.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class ViewResult {

	public static String printScores(Scores scores) {
		Optional<Score> first = scores.getFirst();
		Optional<Score> second = scores.getSecond();
		if (!first.isPresent() || !second.isPresent()) {
			return "        ";
		}
		if (first.get().getScore() == 10 && scores.isSecondScoreNull()) {
			return String.format("   %s    ", first.get().getScore());
		}
		Result result = scores.checkResult();
		return result.renderString(scores);
	}
}
