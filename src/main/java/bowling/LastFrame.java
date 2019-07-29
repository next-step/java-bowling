package bowling;

import bowling.state.FinalState;
import bowling.state.State;
import java.util.Objects;

public class LastFrame implements Frame {

  private static final int LAST_FRAME_NO = 10;

  private State state = new FinalState();

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
  public int score() {
    return state.score().scoreValue();
  }

  @Override
  public int addScore(Score previousScore) {
    return state.addScore(previousScore).scoreValue();
  }

  @Override
  public String toString() {
    return state.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LastFrame lastFrame = (LastFrame) o;
    return Objects.equals(state, lastFrame.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state);
  }
}
