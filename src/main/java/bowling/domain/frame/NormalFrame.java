package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame extends Frame {


  public NormalFrame(int playCount, State state) {
    super(playCount, state);
  }

  public static NormalFrame createFirst() {
    return new NormalFrame(0, new Ready());
  }

  @Override
  public Frame next() {
    if (getPlayCount() == 9) {
      return new FinalFrame(10, new Ready());
    }
    if (getState().isEnd()) {
      return new NormalFrame(getPlayCount() + 1, getState());
    }
    return this;
  }
}
