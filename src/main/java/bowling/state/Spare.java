package bowling.state;

import bowling.FallDownPin;
import java.util.Objects;

public class Spare implements State {

  private static final String SPARE_SYMBOL = "/";
  private static final String ROLL_DELIMITER = "|";

  private FallDownPin firstFallDownPin;
  private FallDownPin secondFallDownPin;

  public Spare(FallDownPin first, FallDownPin second) {
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
  public String toString() {
    return firstFallDownPin.toString() + ROLL_DELIMITER + SPARE_SYMBOL;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Spare spare = (Spare) o;
    return Objects.equals(firstFallDownPin, spare.firstFallDownPin) &&
        Objects.equals(secondFallDownPin, spare.secondFallDownPin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstFallDownPin, secondFallDownPin);
  }

}
