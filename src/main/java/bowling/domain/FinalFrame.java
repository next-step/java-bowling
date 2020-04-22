package bowling.domain;

import static bowling.domain.BonusResult.NULL_RESULT;

import bowling.exception.CannotBowlException;

public class FinalFrame implements Frame {

  private RegularResult regularResult;
  private BonusResult bonusResult;

  public FinalFrame() {
    regularResult = new RegularResult();
    bonusResult = NULL_RESULT;
  }

  public RegularResult getRegularResult() {
    return regularResult;
  }

  public BonusResult getBonusResult() {
    return bonusResult;
  }

  @Override
  public int getRound() {
    return Round.FINAL_ROUND;
  }

  @Override
  public Frame getNextFrame() {
    return NullFrame.of(Round.of(Round.FINAL_ROUND).next());
  }

  @Override
  public Frame roll(int pinCount) throws CannotBowlException {
    if (!regularResult.isFinished()) {
      regularResult.roll(pinCount);
      prepareBonusBowl();
      return this;
    }

    bonusResult.roll(pinCount);
    return this;
  }

  private void prepareBonusBowl() {
    if (regularResult.isFinished()) {
      FrameState state = regularResult.getState();
      bonusResult = new BonusResult(state.getBonusBallCount());
    }
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
  public boolean isEnd() {
    return regularResult.isFinished() && bonusResult.isFinished();
  }

  @Override
  public boolean isFinalFrame() {
    return true;
  }
}
