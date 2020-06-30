package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.Collections;
import java.util.List;

public class Miss implements FrameStatus {

  private final static String BAR = "|";
  private final static String GUTTER = "-";

  private final List<Integer> indexOfScoredFrames;

  public Miss(FrameStatus frameStatus) {
    indexOfScoredFrames = frameStatus.getIndexOfScoredFrames();
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

  @Override
  public String toString(KnockedDownPins pins) {
    if (pins.getRemainingNumber() == 0) {
      throw new IllegalArgumentException("미스가 아닙니다.");
    }

    return (pins.getFirstKnockDownNumber() + BAR + pins.getSecondKnockDownNumber())
        .replaceAll("0", GUTTER);
  }

  @Override
  public String toString() {
    return "Miss{" +
        "indexOfScoredFrames=" + indexOfScoredFrames +
        '}';
  }
}
