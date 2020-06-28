package bowling;

import java.util.Collections;
import java.util.List;

public class RequiredSecondRoll implements FrameStatus {

  private final static int ZERO = 0;

  private final List<Integer> indexOfScoredFrames;

  public RequiredSecondRoll(FrameStatus frameStatus) {
    indexOfScoredFrames = frameStatus.getIndexOfScoredFrames();
  }

  @Override
  public List<Integer> getIndexOfScoredFrames() {
    return Collections.unmodifiableList(indexOfScoredFrames);
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    if (pins.getRemainingNum() == ZERO) {
      return new Spare(this);
    }

    return new Miss(this);
  }

  @Override
  public boolean isOver() {
    return false;
  }
}
