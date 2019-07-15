package bowling;

import java.util.Objects;

public class NormalFrame {

  private static final int FIRST_FRAME_NUMBER = 1;
  private static final int NEXT_FRAME_INTERVAL = 1;
  private int frameNo;
  private FallDownPins fallDownPins;

  public NormalFrame(int frameNo) {
    this.frameNo = frameNo;
  }

  public static NormalFrame first() {
    return new NormalFrame(FIRST_FRAME_NUMBER);
  }

  public NormalFrame roll(int countOfPin) {
    if (isFirstRoll()) {
      fallDownPins = FallDownPins.first(countOfPin);
      return fallDownPins.isFinish() ? nextFrame() : this;
    }

    fallDownPins = fallDownPins.second(countOfPin);
    return nextFrame();

  }

  private NormalFrame nextFrame() {
    return new NormalFrame(frameNo + NEXT_FRAME_INTERVAL);
  }

  private boolean isFirstRoll() {
    return fallDownPins == null;
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
