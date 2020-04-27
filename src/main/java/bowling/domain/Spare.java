package bowling.domain;

import bowling.exception.CannotBowlException;

public class Spare extends FrameState {

  public static final int BONUS_BOWL = 1;
  private static final Spare instance = new Spare();

  private Spare() {
    super(BONUS_BOWL);
  }

  public static Spare getInstance() {
    return instance;
  }

  @Override
  public FrameState bowl(int pinCount) throws CannotBowlException {
    throw new CannotBowlException("프레임을 더 이상 진행할 수 없습니다.");
  }
}
