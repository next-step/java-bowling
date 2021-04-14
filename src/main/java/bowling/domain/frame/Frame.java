package bowling.domain.frame;

import bowling.domain.state.State;

public abstract class Frame {

  private int playCount;
  private State state;
  private static final String END_GAME = "더이상 게임 진행할 수 없습니다.";
  private static final String WRONG_PIN_COUNT = "잘못된 수를 입력했습니다.";

  public Frame(int playCount, State state) {
    validatePlayCount(playCount);
    this.playCount = playCount;
    this.state = state;
  }

  private void validatePlayCount(int playCount) {
    if (playCount < 1 || playCount > 11) {
      throw new IllegalArgumentException(END_GAME);
    }
  }

  public abstract Frame next();

  public void play(int pinCount) {
    validatePinCount(pinCount);
    state = state.play(pinCount);
  }

  private void validatePinCount(int pinCount) {
    if (pinCount < 0 || pinCount > 10) {
      throw new IllegalArgumentException(WRONG_PIN_COUNT);
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
