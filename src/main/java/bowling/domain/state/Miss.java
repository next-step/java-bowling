package bowling.domain.state;

import bowling.domain.PinCount;

public class Miss implements State {

  private final PinCount pinCount;
  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";

  public Miss(PinCount pinCount) {
    this.pinCount = pinCount;
  }

  public Miss(int pinCount) {
    this(new PinCount(pinCount));
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
    return false;
  }

  @Override
  public String getString() {
    return String.valueOf(pinCount.getPinCount());
  }

}
