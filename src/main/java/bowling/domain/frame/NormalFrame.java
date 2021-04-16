package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame extends Frame {

  public NormalFrame(int playCount, State state) {
    super(playCount, state);
  }

  public static NormalFrame createFirst() {
    return create(1);
  }

  public static NormalFrame of(int playCount, State state) {
    return new NormalFrame(playCount, state);
  }

  private static NormalFrame create(int playCount) {
    return NormalFrame.of(playCount, new Ready());
  }

  @Override
  public Frame next() {
    if (getPlayCount() == 9) {
      return FinalFrame.create();
    }
    if (getState().isEnd()) {
      return create(getPlayCount() + 1);
    }

    return NormalFrame.of(getPlayCount(), getState());
  }

}
