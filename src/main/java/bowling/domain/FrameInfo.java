package bowling.domain;

import bowling.exception.CannotBowlException;

public class FrameInfo {

  private RegularResult regularResult;
  private Round round;

  public FrameInfo(Round round) {
    this.regularResult = new RegularResult();
    this.round = round;
  }

  public void roll(int pinCount) throws CannotBowlException {
    regularResult.roll(pinCount);
  }

  public Round nextRound() {
    return round.next();
  }

  public String visualize() {
    return regularResult.visualize();
  }

  public boolean isFull() {
    return regularResult.isFinished();
  }
}
