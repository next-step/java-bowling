package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class Spare implements State {

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";

  private PinCount pinCount;
  private PinCount newPinCount;

  public Spare(PinCount pinCount,PinCount newPinCount) {
    this.pinCount = pinCount;
    this.newPinCount = newPinCount;
  }

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
    return "/";
  }

  @Override
  public Score getScore() {
    return new Score(10, 1);
  }
}
