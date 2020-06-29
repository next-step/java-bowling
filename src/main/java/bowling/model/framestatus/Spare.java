package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Spare implements FrameStatus {

  private final static String BAR = "|";
  private final static String SPARE = "/";
  private final static String GUTTER = "-";

  private final List<Integer> indexOfScoredFrames;

  public Spare(FrameStatus frameStatus) {
    int curIndex = frameStatus.getIndexOfScoredFrames().get(0);

    this.indexOfScoredFrames = Arrays.asList(curIndex, curIndex + 1);
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
    if (pins.getRemainingNum() != 0) {
      throw new IllegalArgumentException("스페어가 아닙니다.");
    }

    return (pins.getFirstKnockDownNum() + BAR + SPARE).replace("0", GUTTER);
  }

  @Override
  public String toString() {
    return "Spare{" +
        "indexOfScoredFrames=" + indexOfScoredFrames +
        '}';
  }
}
