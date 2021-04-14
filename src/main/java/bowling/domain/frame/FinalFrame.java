package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FinalFrame extends Frame {

  public FinalFrame(int playCount, State state) {
    super(playCount, state);
  }

  @Override
  public Frame next() {
    if (getState().isBonus()) {
      return new FinalFrame(10, new Ready());
    }

    if (getState().isEnd()) {
      throw new IllegalArgumentException("");
    }
    return this;
  }

}
