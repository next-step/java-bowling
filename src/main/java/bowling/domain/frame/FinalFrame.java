package bowling.domain.frame;

import bowling.domain.turn.BallRelease;
import bowling.domain.turn.FallenPins;

import java.util.List;

public class FinalFrame extends Frame{

  protected FinalFrame(int round) {
    super(round);
  }

  @Override
  public List<BallRelease> shot(FallenPins fallenPins){
    checkThrowable(fallenPins);
    ballReleases.add(new BallRelease(fallenPins));
    return ballReleases;
  }

  @Override
  protected void checkThrowable(FallenPins pins){
    if(ballReleases.size() <= MAX_THROWABLE_BALLS && fallenPinsStatus()==MAX_FALLEN_PINS){
      return;
    }
    super.checkThrowable(pins);
  }

  @Override
  public boolean checkFinished(){
    if(super.isStrike() || super.isSpare()){
      return false;
    }

    if(ballReleases.size() < MAX_THROWABLE_BALLS && fallenPinsStatus() < MAX_FALLEN_PINS){
      return false;
    }

    return true;
  }

  @Override
  public boolean isStrike() {
    if(ballReleases.size()>=STRIKE_SIZE){
      return head().isStrike();
    }
    return false;
  }

  @Override
  public boolean isSpare() {
    if(ballReleases.size()>=MAX_THROWABLE_BALLS){
      return calculateFirstAndSecondShot(ballReleases)==MAX_FALLEN_PINS;
    }
    return super.isSpare();
  }

  private int calculateFirstAndSecondShot(List<BallRelease> ballReleases){
    return ballReleases.get(0).fallenPins().pins() + ballReleases.get(1).fallenPins().pins();
  }
}
