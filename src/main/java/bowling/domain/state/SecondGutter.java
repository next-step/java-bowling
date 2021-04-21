package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

public class SecondGutter implements State {

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";
  private final Pin firstPin;

  public SecondGutter(int firstPin) {
    this(new Pin(firstPin));
  }

  public SecondGutter(Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State play(Pin fallenPin) {
    throw new IllegalArgumentException(INVALID_END_PLAY);
  }

  @Override
  public boolean isFinish() {
    return true;
  }

  @Override
  public int getPitchCount() {
    return 2;
  }
  @Override
  public Score getScore() {
    return new Score(firstPin.getCount(), 0);
  }

  @Override
  public int getTotalCount() {
    return firstPin.getCount();
  }

  @Override
  public boolean isStrikeOrSpare() {
    return false;
  }

  @Override
  public String toString() {

    if (firstPin.isGutter()) {
      return "-|-";
    }

    return firstPin.toString()+ "|-";
  }
}
