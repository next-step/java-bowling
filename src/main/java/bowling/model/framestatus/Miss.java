package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.KnockedDownPins;
import java.util.Collections;
import java.util.List;

public class Miss implements FrameStatus {

  private final List<Integer> scoringFramesIndexes;

  public Miss(FrameStatus frameStatus) {
    scoringFramesIndexes = frameStatus.getScoringFramesIndexes();
  }

  @Override
  public List<Integer> getScoringFramesIndexes() {
    return Collections.unmodifiableList(scoringFramesIndexes);
  }
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
  public String getResultBy(KnockedDownPins pins) {
    if (pins.getRemainingNumber() == 0) {
      throw new IllegalArgumentException("미스가 아닙니다.");
    }

    return (pins.getFirstKnockDownNumber() + BAR.toString() + pins.getSecondKnockDownNumber())
        .replaceAll("0", GUTTER.toString());
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String toString() {
    return "Miss{" +
        "indexOfScoredFrames=" + scoringFramesIndexes +
        '}';
  }
}
