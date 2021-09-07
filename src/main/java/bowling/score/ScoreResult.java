package bowling.score;

import bowling.dto.ScoreResultDto;
import java.util.Objects;

public class ScoreResult {

  private static final int NOT_CHECK_SCORE = -1;

  private final String scoreMessage;

  private final int score;

  private int totalScore = -1;

  public ScoreResult(final String scoreMessage, final int score) {
    this.scoreMessage = scoreMessage;
    this.score = score;
  }

  public void currentTotal(final int totalSum) {
    this.totalScore = totalSum;
  }

  public boolean isNotCheckScore() {
    return this.score == NOT_CHECK_SCORE;
  }

  public ScoreResultDto from() {
    return ScoreResultDto.of(scoreMessage, totalScore, score);
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ScoreResult that = (ScoreResult) o;
    return score == that.score && totalScore == that.totalScore && Objects.equals(
        scoreMessage, that.scoreMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scoreMessage, score, totalScore);
  }

  @Override
  public String toString() {
    return "ScoreResult{" +
        "scoreMessage='" + scoreMessage + '\'' +
        ", score=" + score +
        ", totalScore=" + totalScore +
        '}';
  }

}
