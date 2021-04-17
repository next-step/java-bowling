package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {


  private static final String INVALID_END_GAME = "더이상 게임 진행할 수 없습니다.";
  private static final int MIN_PLAY_COUNT = 1;
  private static final int MAX_PLAY_COUNT = 10;

  /////
  private Frame next;
  private int playCount;
  private State state;


  public NormalFrame(int playCount, State state) {

    this.playCount = playCount;
    this.state = state;
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


  public Frame createFrame() {
    if (playCount  == 9) {
      return new FinalFrame();
    }

    return new NormalFrame(playCount + 1, new Ready());
  }

  @Override
  public void play(PinCount pinCount) {
    this.state = state.play(pinCount);

    if (state.isEnd()) {
      this.next = createFrame();
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

  @Override
  public Frame nextFrame() {
    if (next != null) {
      return next;
    }
    return this;
  }
}
