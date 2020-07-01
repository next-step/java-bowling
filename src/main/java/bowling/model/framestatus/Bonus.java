package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.KnockedDownPins;
import java.util.Collections;
import java.util.List;

public class Bonus implements FrameStatus {

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
  public boolean isBonus() {
    return true;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    if (pins.getFirstKnockDownNumber() == 10) {
      return STRIKE.toString();
    }

    return String.valueOf(pins.getFirstKnockDownNumber()).replaceAll("0", GUTTER.toString());
  }
}
