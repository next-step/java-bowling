package bowling.domain.frame;

import bowling.domain.turn.BallRelease;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
  protected static final int MAX_THROWABLE_BALLS = 2;
  protected static final int MAX_FALLEN_PINS = 10;

  protected final List<BallRelease> ballRelease;

  public Frame(){
    ballRelease = new ArrayList<>();
  }

  public List<BallRelease> shot(FallenPins fallenPins){
    checkThrowable(fallenPins);
    ballRelease.add(new BallRelease(fallenPins));
    return ballRelease;
  }

  protected int fallenPinsStatus(){
    return ballRelease.stream()
      .map(ball -> ball.fallenPins())
      .mapToInt(fallenPins -> fallenPins.pins())
      .sum();
  }

  protected void checkThrowable(FallenPins pins) {
    if(ballRelease.size() >= MAX_THROWABLE_BALLS){
      throw new CannotThrowBallException();
    }

    if(fallenPinsStatus() + pins.pins() > MAX_FALLEN_PINS){
      throw new CannotThrowBallException();
    }
  }

}
