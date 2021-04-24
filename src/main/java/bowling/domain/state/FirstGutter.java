package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class FirstGutter implements State {

  private static final String INVALID_SCORE = "프레임이 종료된 후에 점수를 생성 할 수 있습니다.";

  @Override
  public State play(Pin fallenPin) {
    if (fallenPin.isGutter()) {
      return new SecondGutter(0);
    }
    if (fallenPin.isStrike()) {
      return new Spare(0, 10);
    }
    return new Miss(0, fallenPin.getCount());
  }

  @Override
  public boolean isFinish() {
    return false;
  }

  @Override
  public int getPitchCount() {
    return 1;
  }

  @Override
  public Score getScore() {
    throw new IllegalStateException(INVALID_SCORE);
  }

  @Override
  public int getTotalCount() {
    return 0;
  }

  @Override
  public String toString() {
    return "-";
  }
}
