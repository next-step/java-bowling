package bowling;

import bowling.state.State;
import java.util.Objects;

public class NormalFrame implements Frame {

  private static final int FIRST_FRAME_NUMBER = 1;
  private static final int NEXT_FRAME_INTERVAL = 1;
  private static final int NORMAL_FRAME_LAST_INDEX = 9;

  private int frameNo;
  private State state;

  public NormalFrame(int frameNo) {
    this.frameNo = frameNo;
    this.state = new FallDownPins();
  }

  public static Frame first() {
    return new NormalFrame(FIRST_FRAME_NUMBER);
  }

  public Frame roll(int countOfPin) {
    state = state.roll(countOfPin);
    return this;
  }

  public Frame nextFrame() {
    if (!isGameEnd()) {
      return this;
    }
    if (frameNo == NORMAL_FRAME_LAST_INDEX) {
      return new LastFrame();
    }
    return new NormalFrame(frameNo + NEXT_FRAME_INTERVAL);
  }

  public int getFrameNo() {
    return frameNo;
  }

  @Override
  public boolean isGameEnd() {
    return state.isFinish();
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
    NormalFrame that = (NormalFrame) o;
    return frameNo == that.frameNo &&
        Objects.equals(state, that.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frameNo, state);
  }
}
