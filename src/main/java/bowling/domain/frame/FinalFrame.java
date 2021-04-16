package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FinalFrame extends Frame {

  private static boolean usedBonus = false;

  public FinalFrame(int playCount, State state) {
    super(playCount, state);
  }

  public static FinalFrame create() {
    return FinalFrame.of(10, new Ready());
  }

  @Override
  public Frame next() {
    if (getState().isBonus() && !usedBonus) {
      usedBonus = true;
      return create();
    }
    return FinalFrame.of(getPlayCount(), getState());
  }

  public static FinalFrame of(int playCount, State state) {
    return new FinalFrame(playCount, state);
  }


}
