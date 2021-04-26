package bowlingstate.domain.state;

import bowlingstate.domain.Score;
import java.util.List;

public class Ready implements State {

  private static final int STRIKE_PIN_COUNT = 10;

  @Override
  public State bowl(int countOfHitPin) {
    if (countOfHitPin == STRIKE_PIN_COUNT) {
      return new Strike();
    }
    return new FirstBowl(countOfHitPin);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public List<String> state() {
    throw new IllegalStateException("프레임이 진행중입니다.");
  }

  @Override
  public Score of() {
    throw new IllegalStateException("프레임이 진행중입니다.");
  }
}
