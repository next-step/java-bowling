package bowling.domain.frame;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;

public class NormalFrame extends Frame {

  private Frame nextFrame;
  private State state;

  protected NormalFrame(int round) {
    super(round);
  }

  @Override
  protected void checkThrowable(FallenPins pins) {
    super.checkThrowable(pins);
  }

  @Override
  public boolean checkFinished() {
    return fallenPins.size() >= MAX_THROWABLE_BALLS || fallenPinsStatus() >= MAX_FALLEN_PINS;
  }

  @Override
  public String show() {
    return null;
  }

}
