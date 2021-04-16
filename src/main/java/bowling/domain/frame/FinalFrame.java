package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FinalFrame implements Frame {

  private static boolean usedBonus = false;
  private final Frame frame;
  private static final int FINAL_PLAY_COUNT = 10;

  public FinalFrame(State state) {
    this.frame = BaseFrame.of(FINAL_PLAY_COUNT, state);
  }

  public static Frame createWithReady() {
    return FinalFrame.of(new Ready());
  }

  public static FinalFrame of(State state) {
    return new FinalFrame(state);
  }

  @Override
  public Frame next() {
    if (getState().isBonus() && !usedBonus) {
      usedBonus = true;
      return createWithReady();
    }
    return FinalFrame.of(getState());
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
    return frame.getState().isEnd();
  }

}
