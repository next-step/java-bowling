package bowling.model;

import java.util.List;

public interface FrameStatus {

  List<Integer> getIndexOfScoredFrames();

  FrameStatus createNextStatusBy(KnockedDownPins pins);

  boolean isOver();
}
