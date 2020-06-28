package bowling;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Spare implements FrameStatus {

  private final List<Integer> indexOfScoredFrames;

  public Spare(FrameStatus frameStatus) {
    int curIndex = frameStatus.getIndexOfScoredFrames().get(0);

    this.indexOfScoredFrames = Arrays.asList(curIndex + 1);
  }

  @Override
  public List<Integer> getIndexOfScoredFrames() {
    return Collections.unmodifiableList(indexOfScoredFrames);
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    return this;
  }

  @Override
  public boolean isOver() {
    return true;
  }
}
