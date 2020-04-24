package bowling.domain;

import bowling.exception.CannotBowlException;

public class NormalFrame implements Frame {

  private FrameInfo frameInfo;
  private Frame next;

  private NormalFrame(Round round) {
    frameInfo = new FrameInfo(round);
    next = NullFrame.of(round.next());
  }

  public static NormalFrame initialize() {
    return new NormalFrame(Round.first());
  }

  public static NormalFrame of(Round round) {
    return new NormalFrame(round);
  }

  public FrameInfo getFrameInfo() {
    return frameInfo;
  }

  @Override
  public int getRound() {
    return frameInfo.getRound();
  }

  @Override
  public int getRolledBowlCount() {
    return frameInfo.getRolledBowlCount();
  }

  @Override
  public Frame getNextFrame() {
    return next;
  }

  @Override
  public Frame roll(int pinCount) throws CannotBowlException {
    frameInfo.roll(pinCount);
    if (frameInfo.isFinished()) {
      next = Frame.of(frameInfo.nextRound());
      return next;
    }

    return this;
  }

  @Override
  public Score calculateBonusScore(int bonusBowlCount) {
    if (bonusBowlCount <= 0) {
      return Score.zero();
    }

    Score additionalScore = next.calculateBonusScore(bonusBowlCount - getRolledBowlCount());
    RegularResult regularResult = frameInfo.getRegularResult();
    Score score = regularResult.getScore(bonusBowlCount);

    return Score.add(score, additionalScore);
  }

  @Override
  public Score calculateScore() {
    RegularResult regularResult = frameInfo.getRegularResult();
    FrameState frameState = FrameState.of(regularResult);
    Score score = frameInfo.getScore();

    return Score.add(score, next.calculateBonusScore(frameState.getBonusBallCount()));
  }

  @Override
  public boolean isEnd() {
    return false;
  }

  @Override
  public boolean isFinalFrame() {
    return false;
  }
}
