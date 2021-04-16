package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {

  private final Frame frame;

  public NormalFrame(int playCount, State state) {
    this.frame = BaseFrame.of(playCount, state);
  }

  public static Frame of(int playCount, State state) {
    return new NormalFrame(playCount, state);
  }

  public static Frame createFirst() {
    return createWithReady(1);
  }

  private static Frame createWithReady(int playCount) {
    return NormalFrame.of(playCount, new Ready());
  }

  @Override
  public Frame next() {
    if (getPlayCount() == 9) {
      return FinalFrame.createWithReady();
    }
    if (getState().isEnd()) {
      return createWithReady(getPlayCount() + 1);
    }

    return NormalFrame.of(getPlayCount(), getState());
  }

  @Override
  public void play(int pinCount) {
    frame.play(pinCount);
  }

  @Override
  public int getPlayCount() {
    return frame.getPlayCount();
  }

  @Override
  public State getState() {
    return frame.getState();
  }

  @Override
  public boolean isEnd() {
    return false;
  }

}
