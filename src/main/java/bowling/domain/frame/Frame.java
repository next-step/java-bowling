package bowling.domain.frame;

import bowling.domain.state.State;

public abstract class Frame {

  private int playCount;
  private State state;

  public Frame(int playCount, State state) {
    this.playCount = playCount;
    this.state = state;
  }

  public abstract Frame next();

  public void play(int pinCount) {
    validatePinCount(pinCount);
    state = state.play(pinCount);
  }

  private void validatePinCount(int pinCount) {
    if (pinCount < 0 || pinCount > 10) {
      throw new IllegalArgumentException();
    }
  }

  public int getPlayCount() {
    return playCount;
  }

  public State getState() {
    return state;
  }

  public boolean isEnd() {
    return false;
  }
}
