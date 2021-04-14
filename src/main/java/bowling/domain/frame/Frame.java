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

/*Frame firstFrame = NormalFrame.createFirst();//0, ready
    firstFrame.play(10);//strike상

    Frame nextFrame = firstFrame.next();
    assertThat(nextFrame.getPlayCount()).isEqualTo(1);//1, strike
    assertThat(nextFrame.getStatus()).isInstanceOf(Strike.class);*/
  public Frame next() {
    if (state.isEnd()) {
      return new Frame(playCount + 1, state);
    }
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

  public State getStatus() {
    return state;
  }

}
