package bowling.state;

import bowling.FallDownPin;
import java.util.Objects;

public class FirstRoll implements State {

  private FallDownPin fallDownPin;

  public FirstRoll(int countOfPin) {
    fallDownPin = new FallDownPin(countOfPin);
  }

  @Override
  public State roll(int countOfPin) {
    if (!validateSumOfFallDownCount(countOfPin)) {
      throw new IllegalArgumentException("한 프레임에서 넘어뜨릴 수 있는 핀의 합은 10을 넘어 갈 수 없습니다.");
    }
    if (fallDownPin.isSpare(new FallDownPin(countOfPin))) {
      return new Spare(fallDownPin, new FallDownPin(countOfPin));
    }
    return new Miss(fallDownPin, new FallDownPin(countOfPin));
  }

  private boolean validateSumOfFallDownCount(int fallDownCount) {
    return fallDownPin.isValidCount(fallDownCount);
  }

  @Override
  public Boolean isFinish() {
    return false;
  }

  @Override
  public String toString() {
    return fallDownPin.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirstRoll firstRoll = (FirstRoll) o;
    return Objects.equals(fallDownPin, firstRoll.fallDownPin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fallDownPin);
  }
}
