package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.Collections;
import java.util.List;

public class RequiredFirstRoll implements FrameStatus {

  private final static String BLANK = "";
  private final List<Integer> indexOfScoredFrames;

  public RequiredFirstRoll(int curIndex) {
    indexOfScoredFrames = Collections.singletonList(curIndex);
  }

  @Override
  public List<Integer> getIndexOfScoredFrames() {
    return Collections.unmodifiableList(indexOfScoredFrames);
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    if (pins.getFirstKnockDownNum() == KnockedDownPins.MAX_NUMBER_OF_PINS) {
      return new Strike(this);
    }

    return new RequiredSecondRoll(this);
  }

  @Override
  public boolean isOver() {
    return false;
  }

  @Override
  public String toString(KnockedDownPins pins) {
    return BLANK;
  }

  @Override
  public String toString() {
    return "RequiredFirstRoll{" +
        "indexOfScoredFrames=" + indexOfScoredFrames +
        '}';
  }
}
