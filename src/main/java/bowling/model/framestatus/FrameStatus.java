package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.List;

public interface FrameStatus {

  List<Integer> getIndexOfScoredFrames();

  FrameStatus createNextStatusBy(KnockedDownPins pins);

  boolean isOver();

  boolean isBonus();

  String toString(KnockedDownPins pins);
}
