package bowlingstate.domain;

public class Score {

  private int score;
  private int left;

  public Score(int score, int left) {
    this.score = score;
    this.left = left;
  }

  public static Score ofStrike() {
    return new Score(10, 2);
  }

  public static Score ofSpare() {
    return new Score(10, 1);
  }

  public static Score ofMiss(int firstPin, int secondPin) {
    return new Score(firstPin + secondPin, 0);
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
