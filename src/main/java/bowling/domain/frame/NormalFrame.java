package bowling.domain.frame;

import bowling.domain.turn.FallenPins;

public class NormalFrame extends Frame {

  protected NormalFrame(int round) {
    super(round);
  }

  @Override
  protected void checkThrowable(FallenPins pins) {
    super.checkThrowable(pins);
  }

  @Override
  public boolean checkFinished() {
    if(ballReleases.size() < MAX_THROWABLE_BALLS && fallenPinsStatus() < MAX_FALLEN_PINS){
      return false;
    }

    return true;
  }

}
