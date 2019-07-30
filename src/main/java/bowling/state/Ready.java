package bowling.state;

import bowling.FallDownPin;
import bowling.Score;

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

  @Override
  public Score score() {
    return new Score(-1,0);
  }

  @Override
  public Score addScore(Score previousScore) {
    return new Score(-1,0);
  }

  @Override
  public String toString() {
    return "";
  }

}
