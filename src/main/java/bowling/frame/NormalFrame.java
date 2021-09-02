package bowling.frame;

import bowling.state.State;
import bowling.state.StateFactory;

public class NormalFrame implements Frame {

  private static final int LIMIT_FRAME = 9;
  private static final int INCREASE_ROUND_NUMBER = 1;

  private final int roundNumber;

  private State state;

  public NormalFrame(final int round) {
    this.roundNumber = round;
    this.state = StateFactory.startPitch();
  }

  @Override
  public Frame play(final int pinCount) {
    state = state.nextPitch(pinCount);

    if (state.isFinish()) {
      return createFrame();
    }
    return this;
  }

  private Frame createFrame() {
    if (roundNumber < LIMIT_FRAME) {
      return new NormalFrame(roundNumber + INCREASE_ROUND_NUMBER);
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