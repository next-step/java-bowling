package bowling;

import java.util.Objects;

public class Score {

  private static final int STRIKE_SPARE_SCORE = 10;
  private static final int STRIKE_REMAIN_COUNT = 2;
  private static final int SPARE_REMAIN_COUNT = 1;
  private static final int UNDER_SCORE = -1;
  private static final int UNDER_SCORE_COUNT = 0;
  private static final int DECREASE_COUNT = 1;
  private static final int COMPLETE_CONDITION_COUNT = 0;

  private int score;
  private int remainBowlCount;

  public Score(int score, int remainBowlCount) {
    this.score = score;
    this.remainBowlCount = remainBowlCount;
  }

  public static Score strike() {
    return new Score(STRIKE_SPARE_SCORE, STRIKE_REMAIN_COUNT);
  }

  public static Score spare() {
    return new Score(STRIKE_SPARE_SCORE, SPARE_REMAIN_COUNT);
  }

  public static Score noFinishScore() {
    return new Score(UNDER_SCORE, UNDER_SCORE_COUNT);
  }

  public int getScore() {
    return score;
  }

  public boolean isCompleteScore() {
    return remainBowlCount == COMPLETE_CONDITION_COUNT;
  }

  public Score addScore(Score addScore) {
    return new Score(score + addScore.getScore(), remainBowlCount - DECREASE_COUNT);
  }

  @Override
  public String toString() {
    return "Score{" +
        "score=" + score +
        ", remainBowlCount=" + remainBowlCount +
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
        remainBowlCount == score1.remainBowlCount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(score, remainBowlCount);
  }

}
