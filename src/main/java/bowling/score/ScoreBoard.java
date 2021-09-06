package bowling.score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ScoreBoard {

  private final LinkedList<ScoreResult> scoreResults;

  private int totalScore = 0;

  public ScoreBoard() {
    this.scoreResults = new LinkedList<>();
  }

  public void addScoreResult(ScoreResult scoreResult) {
    if (!scoreResult.isNotCheckScore()) {
      totalScore = scoreResult.addTotalScore(totalScore);
    }
    scoreResults.add(scoreResult);
  }

  public List<ScoreResult> getScoreResults() {
    return Collections.unmodifiableList(scoreResults);
  }

  @Override
  public String toString() {
    return "ScoreBoard{" +
        "scoreResults=" + scoreResults +
        ", totalScore=" + totalScore +
        '}';
  }
}
