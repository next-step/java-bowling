package bowling.state;

import bowling.FallDownPin;
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
