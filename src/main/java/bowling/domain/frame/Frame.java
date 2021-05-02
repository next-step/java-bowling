package bowling.domain.frame;

import bowling.domain.turn.BallRelease;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotMakeFrameException;
import bowling.error.CannotThrowBallException;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
  protected static final int MAX_THROWABLE_BALLS = 2;
  protected static final int STRIKE_SIZE = 1;
  protected static final int MAX_FALLEN_PINS = 10;
  private static final int LAST_FRAME = 10;

  protected final int round;

  protected final List<BallRelease> ballReleases;

  protected Frame(int round){
    ballReleases = new ArrayList<>();
    this.round = round;
  }

  public List<BallRelease> shot(FallenPins fallenPins){
    checkThrowable(fallenPins);
    ballReleases.add(new BallRelease(fallenPins));
    return ballReleases;
  }

  protected int fallenPinsStatus(){
    return ballReleases.stream()
      .map(ball -> ball.fallenPins())
      .mapToInt(fallenPins -> fallenPins.pins())
      .sum();
  }

  protected void checkThrowable(FallenPins pins) {
    if(ballReleases.size() >= MAX_THROWABLE_BALLS){
      throw new CannotThrowBallException();
    }

    if(fallenPinsStatus() + pins.pins() > MAX_FALLEN_PINS){
      throw new CannotThrowBallException();
    }
  }

  public static Frame of(int round){
    if(round < 1|| round > LAST_FRAME){
      throw new CannotMakeFrameException();
    }

    if(round == LAST_FRAME){
      return new FinalFrame(round);
    }

    return new NormalFrame(round);
  }

  public Frame makeNextRound(){
    return of(round+1);
  }

  public List<BallRelease> ballReleases(){
    return ballReleases;
  }

  public boolean isStrike() {
    return ballReleases.size()==STRIKE_SIZE && fallenPinsStatus() == MAX_FALLEN_PINS;
  }

  public boolean isSpare(){
    return ballReleases.size()==MAX_THROWABLE_BALLS && fallenPinsStatus() == MAX_FALLEN_PINS;
  }

  public abstract boolean checkFinished();
}
