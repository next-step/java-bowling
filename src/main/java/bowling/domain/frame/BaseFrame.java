package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.state.State;

public class BaseFrame implements Frame {

  private final int playCount;
  private State state;
  private static final String INVALID_END_GAME = "더이상 게임 진행할 수 없습니다.";
  private static final int MIN_PLAY_COUNT = 1;
  private static final int MAX_PLAY_COUNT = 10;

  public BaseFrame(int playCount, State state) {
    validatePlayCount(playCount);
    this.playCount = playCount;
    this.state = state;
  }

  public static BaseFrame of(int playCount, State state) {
    return new BaseFrame(playCount, state);
  }

  private void validatePlayCount(int playCount) {
    if (playCount < MIN_PLAY_COUNT || playCount > MAX_PLAY_COUNT) {
      throw new IllegalArgumentException(INVALID_END_GAME);
    }
  }

  @Override
  public Frame next() {
    return this;
  }

  @Override
  public void play(PinCount pinCount) {
    state = state.play(pinCount);
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
