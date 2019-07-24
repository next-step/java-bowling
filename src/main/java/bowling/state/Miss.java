package bowling.state;

import bowling.FallDownPin;

public class Miss implements State {

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
    return firstFallDownPin.toString()+"|"+secondFallDownPin.toString();
  }
}
