package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FinalFrame extends Frame {

  private static boolean usedBonus = false;

  public FinalFrame(int playCount, State state) {
    super(playCount, state);
  }

  @Override
  public Frame next() {
    if (getState().isBonus() && !usedBonus) {
      usedBonus = true;
      return new FinalFrame(10, new Ready());
    }
    return this;
  }

  @Override
  public boolean isEnd() {
    return getState().isEnd();
  }

  @Override
  public Frame get() {
    return new FinalFrame(getPlayCount(), getState());
  }


}
