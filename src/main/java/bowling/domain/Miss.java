package bowling.domain;

import bowling.exception.CannotBowlException;

public class Miss extends FrameState {

  public static final int BONUS_BOWL = 0;
  private static final Miss instance = new Miss();

  private Miss() {
    super(BONUS_BOWL);
  }

  public static Miss getInstance() {
    return instance;
  }

  @Override
  public FrameState bowl(int pinCount) throws CannotBowlException {
    throw new CannotBowlException("프레임을 더 이상 진행할 수 없습니다.");
  }
}
