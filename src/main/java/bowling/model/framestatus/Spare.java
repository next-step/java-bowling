package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.KnockedDownPins;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Spare implements FrameStatus {

  private final List<Integer> indexOfScoredFrames;

  public Spare(FrameStatus frameStatus) {
    int currentIndex = frameStatus.getIndexOfScoredFrames().get(0);

    this.indexOfScoredFrames = Arrays.asList(currentIndex, currentIndex + 1);
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
  public String getResultBy(KnockedDownPins pins) {
    if (pins.getRemainingNumber() != 0) {
      throw new IllegalArgumentException("스페어가 아닙니다.");
    }

    return (String.valueOf(pins.getFirstKnockDownNumber()) + BAR + SPARE).replace("0", GUTTER.toString());
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String toString() {
    return "Spare{" +
        "indexOfScoredFrames=" + indexOfScoredFrames +
        '}';
  }
}
