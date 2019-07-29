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

  public Score addScore(int score) {
    return new Score(this.score + score, this.remainTimes - 1);
  }

  public boolean hasNoAdditionalScore() {
    return remainTimes == 0;
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
