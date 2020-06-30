package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.Collections;
import java.util.List;

public class Bonus implements FrameStatus {

  private final static String STRIKE = "X";
  private final static String GUTTER = "-";

  @Override
  public List<Integer> getIndexOfScoredFrames() {
    return Collections.emptyList();
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
    if (pins.getFirstKnockDownNumber() == 10) {
      return STRIKE;
    }

    return String.valueOf(pins.getFirstKnockDownNumber()).replaceAll("0", GUTTER);
  }
}
