package bowling.domain.frame;

import bowling.domain.turn.BallRelease;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

import java.util.List;

public class NormalFrame extends Frame {

  public NormalFrame(int round) {
    super(round);
  }

  @Override
  protected void checkThrowable(FallenPins pins) {
    super.checkThrowable(pins);
  }

}
