package bowling;

import java.util.Objects;

public class Score {

  private int score;
  private int remainTimes;

  public Score(int score, int remainTimes) {
    this.score = score;
    this.remainTimes = remainTimes;
  }

  public static Score strike() {
    return new Score(10, 2);
  }

  public static Score spare() {
    return new Score(10, 1);
  }

  public static Score defaultScore() {
    return new Score(-1, 0);
  }

  public Score addScore(int score, int minusCount) {
    return new Score(this.score + score, this.remainTimes - minusCount);
  }

  public boolean hasNoAdditionalScore() {
    return remainTimes == 0;
  }

  public int scoreValue() {
    return score;
  }

  @Override
  public String toString() {
    return "Score{" +
        "score=" + score +
        ", remainTimes=" + remainTimes +
        '}';
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
    return score == score1.score &&
        remainTimes == score1.remainTimes;
  }

  @Override
  public int hashCode() {
    return Objects.hash(score, remainTimes);
  }

}
