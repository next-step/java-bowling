package bowling.domain.bowlresult;

import bowling.domain.Trial;
import bowling.domain.framestate.FrameState;
import bowling.domain.framestate.NotPlayed;
import bowling.exception.CannotBowlException;

public class RegularResult extends BowlResult {

  public static RegularResult NULL_RESULT = new RegularResult();

  private FrameState state;

  public RegularResult() {
    first = Trial.initialize();
    second = Trial.initialize();
    state = NotPlayed.getInstance();
  }

  @Override
  public FrameState getState() {
    return state;
  }

  @Override
  public void roll(int pinCount) throws CannotBowlException {
    if (isFinished()) {
      throw new CannotBowlException("이번 프레임에서 가능한 최대 시도를 넘었습니다.");
    }

    state = state.bowl(pinCount);
    if (first.isNotPlayed()) {
      first.roll(pinCount);
      second.blockIfStrike(first);
      return;
    }

    second.roll(pinCount);
    second.validateSecondTrial(first);
  }
}
