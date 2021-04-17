package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class SecondGutter implements State {

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";

  @Override
  public State play(PinCount pinCount) {
    throw new IllegalArgumentException(INVALID_END_PLAY);
  }

  @Override
  public boolean isEnd() {
    return true;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String getString() {
    return "-";
  }

  //?
  @Override
  public Score getScore() {
    return new Score(0,0);
  }
}
