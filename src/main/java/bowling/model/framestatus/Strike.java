package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Strike implements FrameStatus {

  private final static String STRIKE = "X";

  private final List<Integer> indexOfScoredFrames;

  public Strike(FrameStatus frameStatus) {
    int curIndex = frameStatus.getIndexOfScoredFrames().get(0);

    this.indexOfScoredFrames = Arrays.asList(curIndex, curIndex + 1, curIndex + 2);
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
    if (pins.getFirstKnockDownNum() != 10) {
      throw new IllegalArgumentException("스트라이크가 아닙니다.");
    }

    return STRIKE;
  }

  @Override
  public String toString() {
    return "Strike{" +
        "indexOfScoredFrames=" + indexOfScoredFrames +
        '}';
  }
}
