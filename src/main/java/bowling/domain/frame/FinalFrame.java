package bowling.domain.frame;

import bowling.domain.turn.BallRelease;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

import java.util.List;

public class FinalFrame extends Frame{

  public FinalFrame(int round) {
    super(round);
  }

  @Override
  public List<BallRelease> shot(FallenPins fallenPins){
    checkThrowable(fallenPins);
    ballRelease.add(new BallRelease(fallenPins));
    return ballRelease;
  }

  @Override
  protected void checkThrowable(FallenPins pins){
    if(ballRelease.size() <= MAX_THROWABLE_BALLS && fallenPinsStatus()==MAX_FALLEN_PINS){
      return;
    }
    super.checkThrowable(pins);
  }
}
