package bowling.domain;

import bowling.exception.CannotBowlException;

public class NormalFrame implements Frame {

  private FrameInfo frameInfo;
  private Frame next;

  public NormalFrame(Round round) {
    frameInfo = new FrameInfo(round);
    next = NULL_FRAME;
  }

  @Override
  public Frame roll(int pinCount) throws CannotBowlException {
    frameInfo.roll(pinCount);
    if (frameInfo.isFull()) {
      next = Frame.of(frameInfo.nextRound());
      return next;
    }

    return this;
  }

  @Override
  public Score calculateBonusScore(int bonusBowlCount) {
    return null;
  }

  @Override
  public Score calculateScore() {
    return null;
  }

  @Override
  public String visualize() {
    return frameInfo.visualize();
  }

  @Override
  public boolean isEnd() {
    return false;
  }
}
