package bowling.domain;

public class Score {

  private int score;
  private int left;

  public Score(int score, int left) {
    this.score = score;
    this.left = left;
  }

  public int getScore() {
    return this.score;
  }

  public boolean canCalculateScore() {
    return left == 0;
  }

  public void bowl(int countOfPins) {
    this.score += countOfPins;
    this.left -= 1;
  }

  public static Score of(ScoreSymbol symbol, Pins pins) {
    if (symbol == ScoreSymbol.STRIKE) {
      return new Score(10, 2);
    }
    if (symbol == ScoreSymbol.SPARE) {
      return new Score(10, 1);
    }
    return new Score(pins.totalHitPin(), 0);
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

    if (score != score1.score) {
      return false;
    }
    return left == score1.left;
  }

  @Override
  public int hashCode() {
    int result = score;
    result = 31 * result + left;
    return result;
  }
}
