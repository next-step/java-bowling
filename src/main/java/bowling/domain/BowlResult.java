package bowling.domain;

import bowling.exception.CannotBowlException;

public abstract class BowlResult {

  static final String DELIMITER = "|";

  Trial first;
  Trial second;

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

  public String visualize() {
    if (!second.isPlayed()) {
      return first.visualize();
    }

    return String.join(DELIMITER, first.visualize(), second.visualize(first));
  }
}