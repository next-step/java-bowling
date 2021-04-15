package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame extends Frame {

  public NormalFrame(int playCount, State state) {
    super(playCount, state);
  }

  public static NormalFrame createFirst() {
    return new NormalFrame(1, new Ready());
  }

  @Override
  public Frame next() {
    if (getPlayCount() == 9) {
      return FinalFrame.of(10, new Ready());
    }
    if (getState().isEnd()) {
      return NormalFrame.of(getPlayCount() + 1, new Ready());
    }
    return NormalFrame.of(getPlayCount(), getState());
  }

  public static NormalFrame of(int playCount, State state) {
    return new NormalFrame(playCount, state);
  }

}
