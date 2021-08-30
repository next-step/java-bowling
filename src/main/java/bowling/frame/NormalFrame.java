package bowling.frame;

import bowling.state.State;
import bowling.state.StateFactory;

public class NormalFrame implements Frame {

  public static final int LIMIT_FRAME = 9;

  private State state;

  public NormalFrame() {
    this.state = StateFactory.startPitch();
  }

  @Override
  public Frame play(final int pinCount, final int size) {
    state = state.nextPitch(pinCount);

    if (state.isFinish()) {
      return createFrame(size);
    }
    return this;
  }

  private Frame createFrame(final int size) {
    if (size < LIMIT_FRAME) {
      return new NormalFrame();
    }
    return new FinalFrame();
  }

  @Override
  public String getScore() {
    return state.score();
  }

  @Override
  public boolean isGameEnd() {
    return false;
  }
}