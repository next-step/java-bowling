package bowling.score;

import java.util.List;

public class ScoreCalculator {

  public static int totalSum(List<ScoreResult> scoreResults) {
    return scoreResults
        .stream()
        .filter(scoreResult -> !scoreResult.isNotCheckScore())
        .mapToInt(scoreResult -> scoreResult.from().getScore())
        .sum();
  }
}