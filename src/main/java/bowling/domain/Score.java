package bowling.domain;

import bowling.domain.turn.FallenPins;
import bowling.error.CannotCalculateException;

public class Score {
  private static final int FULL_SCORE = 10;
  private static final int STRIKE_LEFT = 2;
  private static final int SPARE_LEFT = 1;
  private static final int MISS_LEFT = 0;

  private final int score;
  private final int left;

  public Score(int score, int left) {
    this.score = score;
    this.left = left;
  }

  public static Score ofStrike() {
    return new Score(FULL_SCORE,STRIKE_LEFT);
  }

  public static Score ofSpare() {
    return new Score(FULL_SCORE,SPARE_LEFT);
  }

  public static Score ofMiss(FallenPins totalPins){
    return new Score(totalPins.pins(), MISS_LEFT);
  }

  public Score bowl(int countOfPins) {
    return new Score(score + countOfPins, left - 1);
  }

  public int getScore() {
    if (!canCalculateScore()) {
      throw new CannotCalculateException();
    }
    return this.score;
  }

  public boolean canCalculateScore() {
    return left == 0;
  }

}
