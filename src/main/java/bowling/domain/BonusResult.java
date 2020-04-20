package bowling.domain;

import static bowling.domain.Trial.NOT_PLAYED_SIGN;

import bowling.exception.CannotBowlException;

public class BonusResult extends BowlResult {

  public BonusResult(int bonusBallCount) {
    if (bonusBallCount > 2 || bonusBallCount < 0) {
      throw new IllegalArgumentException("보너스 볼은 최소 0개 최대 2개입니다.");
    }

    if (bonusBallCount == 2) {
      first = Trial.initialize();
      second = Trial.initialize();
    }

    if (bonusBallCount == 1) {
      first = Trial.initialize();
      second = Trial.block();
    }

    if (bonusBallCount == 0) {
      first = Trial.block();
      second = Trial.block();
    }
  }

  public BonusResult() { }

  @Override
  public void roll(int pinCount) throws CannotBowlException {
    if (isFinished()) {
      throw new CannotBowlException("이번 프레임에서 가능한 최대 시도를 넘었습니다.");
    }

    if (first.isNotPlayed()) {
      first.roll(pinCount);
      return;
    }

    second.roll(pinCount);
    if (!first.isStrike()) {
      second.validateSecondTrial(first);
    }
  }

  public static BonusResult NULL_RESULT = new BonusResult() {

    @Override
    public void roll(int pinCount) {
    }

    @Override
    public boolean isFinished() {
      return false;
    }

    @Override
    public FrameState getState() {
      return null;
    }

    @Override
    public String visualize() {
      return NOT_PLAYED_SIGN;
    }
  };
}
