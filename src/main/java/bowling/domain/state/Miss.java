package bowling.domain.state;

import bowling.domain.PinCount;
import bowling.domain.Score;

public class Miss implements State {

  private final PinCount pinCount;
  private final PinCount newPinCount;

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";

  public Miss(int pinCount, int newPinCount) {
    this(new PinCount(pinCount), new PinCount(newPinCount));
  }

  public Miss(PinCount pinCount, PinCount newPinCount) {
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
    return false;
  }

  @Override
  public String getString() {
    return String.valueOf(pinCount.getPinCount());
  }

  @Override
  public Score getScore() {
    return new Score(pinCount.sum(newPinCount), 0);
  }

}
