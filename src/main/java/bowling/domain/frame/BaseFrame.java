package bowling.domain.frame;

import bowling.domain.state.State;

public class BaseFrame implements Frame {

  private int playCount;
  private State state;
  private static final String END_GAME = "더이상 게임 진행할 수 없습니다.";
  private static final String WRONG_PIN_COUNT = "잘못된 수를 입력했습니다.";
  private static final int START_PLAY_COUNT = 1;
  private static final int END_PLAY_COUNT = 10;
  private static final int MIN_PIN_COUNT = 0;
  private static final int MAX_PIN_COUNT = 10;

  public BaseFrame(int playCount, State state) {
    validatePlayCount(playCount);
    this.playCount = playCount;
    this.state = state;
  }

  public static BaseFrame of(int playCount, State state) {
    return new BaseFrame(playCount, state);
  }

  private void validatePlayCount(int playCount) {
    if (playCount < START_PLAY_COUNT || playCount > END_PLAY_COUNT) {
      throw new IllegalArgumentException(END_GAME);
    }
  }

  @Override
  public Frame next() {
    return this;
  }

  @Override
  public void play(int pinCount) {
    validatePinCount(pinCount);
    state = state.play(pinCount);
  }

  private void validatePinCount(int pinCount) {
    if (pinCount < MIN_PIN_COUNT || pinCount > MAX_PIN_COUNT) {
      throw new IllegalArgumentException(WRONG_PIN_COUNT);
    }
  }

  @Override
  public int getPlayCount() {
    return playCount;
  }

  @Override
  public State getState() {
    return state;
  }

  @Override
  public boolean isEnd() {
    return false;
  }

}
