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

  public FrameState getState() {
    if (first.isStrike()) {
      return FrameState.STRIKE;
    }

    if (second.isSpare(first)) {
      return FrameState.SPARE;
    }

    if (isFinished()) {
      return FrameState.MISS;
    }

    return FrameState.IN_PROGRESS;
  }

}