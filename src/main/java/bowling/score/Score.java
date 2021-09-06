package bowling.score;

import bowling.dto.ScoreDto;
import java.util.Objects;

public class Score {

  private static final int MAX_SCORE = 10;
  private static final int DEFAULT_BALL_COUNT = 0;
  private static final int SPARE_BALL_COUNT = 1;
  private static final int STRIKE_BALL_COUNT = 2;
  private static final String MSG_ERROR_LIMIT_BALL_COUNT = "계산할 수 없습니다.";

  private final int score;

  private final int ballCount;

  private Score(final int score, final int ballCount) {
    this.score = score;
    this.ballCount = ballCount;
  }

  public static Score strike() {
    return new Score(MAX_SCORE, STRIKE_BALL_COUNT);
  }

  public static Score spare() {
    return new Score(MAX_SCORE, SPARE_BALL_COUNT);
  }

  public static Score miss(final int score) {
    return new Score(score, DEFAULT_BALL_COUNT);
  }

  public Score sum(final int pinCount) {
    validationBallCount();
    return new Score(score + pinCount, ballCount - SPARE_BALL_COUNT);
  }

  private void validationBallCount() {
    if (isFinishBallCount()) {
      throw new IllegalArgumentException(MSG_ERROR_LIMIT_BALL_COUNT);
    }
  }

  public boolean isFinishBallCount() {
    return ballCount == DEFAULT_BALL_COUNT;
  }

  public ScoreDto from() {
    if(!isFinishBallCount()){
      throw new RuntimeException();
    }
    return ScoreDto.from(score);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Score score1 = (Score) o;
    return score == score1.score && ballCount == score1.ballCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(score, ballCount);
  }

  @Override
  public String toString() {
    return "Score{" +
        "score=" + score +
        ", ballCount=" + ballCount +
        '}';
  }
}
