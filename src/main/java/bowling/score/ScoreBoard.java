package bowling.score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ScoreBoard {

  private final LinkedList<ScoreResult> scoreResults;

  public ScoreBoard() {
    this.scoreResults = new LinkedList<>();
  }

  public void addScoreResult(ScoreResult scoreResult) {
    scoreResults.add(scoreResult);
    returnTotal(scoreResult);
  }

  private void returnTotal(final ScoreResult scoreResult) {
    scoreResult.currentTotal(ScoreCalculator.totalSum(scoreResults));
  }

  public List<ScoreResult> getScoreResults() {
    return Collections.unmodifiableList(scoreResults);
  }
}
