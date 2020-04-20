package bowling.domain;

import bowling.exception.CannotBowlException;

public class RegularResult extends BowlResult {

  public RegularResult() {
    first = Trial.initialize();
    second = Trial.initialize();
  }

  @Override
  public void roll(int pinCount) throws CannotBowlException {
    if (isFinished()) {
      throw new CannotBowlException("이번 프레임에서 가능한 최대 시도를 넘었습니다.");
    }

    if (first.isNotPlayed()) {
      first.roll(pinCount);
      second.blockIfStrike(first);
      return;
    }

    second.roll(pinCount);
    second.validateSecondTrial(first);
  }
}
