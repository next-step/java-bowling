package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.Collections;
import java.util.List;

public class RequiredSecondRoll implements FrameStatus {

  private final static String GUTTER = "-";
  private final static Integer ZERO = 0;

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
    if (pins.getRemainingNumber() == ZERO) {
      return new Spare(this);
    }

    return new Miss(this);
  }

  @Override
  public boolean isOver() {
    return false;
  }

  @Override
  public String toString(KnockedDownPins pins) {
    return String.valueOf(pins.getFirstKnockDownNumber()).replace(ZERO.toString(), GUTTER);
  }

  @Override
  public String toString() {
    return "RequiredSecondRoll{" +
        "indexOfScoredFrames=" + indexOfScoredFrames +
        '}';
  }
}
