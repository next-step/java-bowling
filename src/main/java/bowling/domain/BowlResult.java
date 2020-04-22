package bowling.domain;

import bowling.exception.CannotBowlException;

public abstract class BowlResult {

  Trial first;
  Trial second;

  public Trial getFirst() {
    return first;
  }

  public Trial getSecond() {
    return second;
  }

  public abstract void roll(int pinCount) throws CannotBowlException;

  public boolean isFinished() {
    return !first.isNotPlayed() && !second.isNotPlayed();
  }
}