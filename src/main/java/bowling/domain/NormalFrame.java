package bowling.domain;

import bowling.exception.CannotBowlException;

public class NormalFrame implements Frame {

  private FrameInfo frameInfo;
  private Frame next;

  private NormalFrame(Round round) {
    frameInfo = new FrameInfo(round);
    next = NULL_FRAME;
  }

  public static NormalFrame initialize() {
    return new NormalFrame(Round.first());
  }

  public static NormalFrame of(Round round) {
    return new NormalFrame(round);
  }

  @Override
  public int getRound() {
    return frameInfo.getRound();
  }

  @Override
  public Frame getNextFrame() {
    return next;
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
