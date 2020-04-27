package bowling.domain;

import bowling.exception.CannotBowlException;

public abstract class FrameState {

  private int bonusBallCount;

  public FrameState(int bonusBallCount) {
    this.bonusBallCount = bonusBallCount;
  }

  public int getBonusBallCount() {
    return bonusBallCount;
  }

  public abstract FrameState bowl(int pinCount) throws CannotBowlException;
}
