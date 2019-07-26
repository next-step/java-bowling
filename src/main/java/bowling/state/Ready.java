package bowling.state;

import bowling.FallDownPin;

public class Ready implements State {

  @Override
  public State roll(int countOfPin) {
    if (new FallDownPin(countOfPin).isStrike()) {
      return new Strike();
    }
    return new FirstRoll(countOfPin);
  }

  @Override
  public Boolean isFinish() {
    return false;
  }
}
