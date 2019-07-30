package bowling.state;

import bowling.FallDownPin;
import bowling.Score;
import java.util.Objects;

public class Miss implements State {

  private static final String ROLL_DELIMITER = "|";

  private FallDownPin firstFallDownPin;
  private FallDownPin secondFallDownPin;

  public Miss(FallDownPin first, FallDownPin second) {
    this.firstFallDownPin = first;
    this.secondFallDownPin = second;
  }

  @Override
  public State roll(int countOfPin) {
    throw new RuntimeException("해당프레임은 끝났습니다.");
  }

  @Override
  public Boolean isFinish() {
    return true;
  }

  @Override
  public Score score() {
    return new Score(firstFallDownPin.getFallDownCount() + secondFallDownPin.getFallDownCount(), 0);
  }

  @Override
  public Score addScore(Score previousScore) {
    Score score = previousScore.addScore(firstFallDownPin.getFallDownCount(), 1);
    if(score.hasNoAdditionalScore()){
      return score;
    }
    return score.addScore(secondFallDownPin.getFallDownCount(),1);
  }

  @Override
  public String toString() {
    return firstFallDownPin.toString() + ROLL_DELIMITER + secondFallDownPin.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Miss miss = (Miss) o;
    return Objects.equals(firstFallDownPin, miss.firstFallDownPin) &&
        Objects.equals(secondFallDownPin, miss.secondFallDownPin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstFallDownPin, secondFallDownPin);
  }

}
