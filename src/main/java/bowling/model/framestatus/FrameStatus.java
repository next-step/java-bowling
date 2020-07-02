package bowling.model.framestatus;

import bowling.model.KnockedDownPins;
import java.util.List;

public interface FrameStatus {

  List<Integer> getScoringFramesIndexes();

  int getSizeOfScoringFramesIndexes();

  FrameStatus createNextStatusBy(KnockedDownPins pins);

  boolean isOver();

  boolean isBonus();

  String getResultBy(KnockedDownPins pins);
}
