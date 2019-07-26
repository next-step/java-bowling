package bowling;

import bowling.state.FirstRoll;
import bowling.state.State;
import bowling.state.Strike;

public class StateFactory {

  public static State make(int fallDownCount) {
    if (new FallDownPin(fallDownCount).isStrike()) {
      return new Strike();
    }
    return new FirstRoll(fallDownCount);
  }

}
