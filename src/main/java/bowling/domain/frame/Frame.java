package bowling.domain.frame;

import bowling.domain.state.State;

public class Frame {

  private int playCount;
  private State state;

  public Frame(int playCount, State state) {
    this.playCount = playCount;
    this.state = state;
  }


  public void play(int pinCount) {
    validateBawlingCount(pinCount);
    state = state.play(pinCount);
  }

  public Frame next() {
    return this;
  }

  private void validateBawlingCount(int pinCount) {
    if (pinCount < 1 || pinCount > 10) {
      throw new IllegalArgumentException();
    }
  }

  public int getPlayCount() {
    return playCount;
  }

  public State getState() {
    return state;
  }

}
