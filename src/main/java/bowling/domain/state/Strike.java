package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class Strike implements State {

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
    return true;
  }

  @Override
  public String getString() {
    return "x";
  }

  @Override
  public Score getScore() {
    return new Score(10, 2);
  }
}
