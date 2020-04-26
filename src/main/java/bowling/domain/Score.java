package bowling.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class Score {

  private static final Score NULL = new Score();
  private static final Score ZERO = new Score(0);

  private int score;

  private Score() {}

  private Score(int score) {
    this.score = score;
  }

  public static Score ofNull() {
    return NULL;
  }

  public static Score zero() {
    return ZERO;
  }

  public static Score of(int... scores) {
    return new Score(IntStream.of(scores).sum());
  }

  public static Score of(Trial trial) {
    if (trial.isNotPlayed()) {
      return NULL;
    }

    return Score.of(trial.getPinCount());
  }

  public static Score add(Score score1, Score score2) {
    if (score1 == NULL || score2 == NULL) {
      return NULL;
    }

    return new Score(score1.score + score2.score);
  }

  public int getScore() {
    return score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Score score1 = (Score) o;
    return score == score1.score;
  }

  @Override
  public int hashCode() {
    return Objects.hash(score);
  }
}
