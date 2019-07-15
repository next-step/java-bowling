package bowling;

import java.util.Objects;

public class NormalFrame implements Frame {

  private static final int FIRST_FRAME_NUMBER = 1;
  private static final int NEXT_FRAME_INTERVAL = 1;
  private static final int NORMAL_FRAME_LAST_INDEX= 9;

  private int frameNo;
  private FallDownPins fallDownPins;

  public NormalFrame(int frameNo) {
    this.frameNo = frameNo;
  }

  public static Frame first() {
    return new NormalFrame(FIRST_FRAME_NUMBER);
  }

  public Frame roll(int countOfPin) {
    if (isFirstRoll()) {
      fallDownPins = FallDownPins.first(countOfPin);
      return fallDownPins.isFinish() ? nextFrame() : this;
    }

    fallDownPins = fallDownPins.roll(countOfPin);
    return nextFrame();

  }

  private Frame nextFrame() {
    if (frameNo == NORMAL_FRAME_LAST_INDEX) {
      return new LastFrame();
    }
    return new NormalFrame(frameNo + NEXT_FRAME_INTERVAL);
  }

  private boolean isFirstRoll() {
    return fallDownPins == null;
  }

  public int getFrameNo() {
    return frameNo;
  }

  @Override
  public boolean isGameEnd() {
    return false;
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
        Objects.equals(fallDownPins, that.fallDownPins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frameNo, fallDownPins);
  }
}
