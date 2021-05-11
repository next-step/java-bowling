package bowling.domain.state.running;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.finished.Strike;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotCalculateException;

public class Ready extends Running {
  private static final String EMPTY = "";

  private Ready() {

  }

  public static Ready of() {
    return new Ready();
  }


  @Override
  public State bowl(FallenPins pins) {
    if (pins.isStrike()) {
      return Strike.of(pins);
    }

    return FirstShot.of(pins);
  }

  @Override
  public String show() {
    return EMPTY;
  }

  @Override
  public Score addScore(Score score) {
    throw new CannotCalculateException();
  }
}
