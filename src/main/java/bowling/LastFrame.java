package bowling;

import bowling.state.State;

public class LastFrame implements Frame {

  private static final int LAST_FRAME_NO = 10;

  private State state = new LastFallDownPins();

  public LastFrame roll(int countOfPin) {
    state = state.roll(countOfPin);
    return this;
  }

  @Override
  public boolean isGameEnd() {
    return state.isFinish();
  }

  @Override
  public int getFrameNo() {
    return LAST_FRAME_NO;
  }

  @Override
  public Frame nextFrame() {
    return this;
  }

  @Override
  public String toString() {
    return state.toString();
  }
}
