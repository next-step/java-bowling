package bowling.model.framestatus;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import java.util.List;

public interface FrameStatus {

  Frame getNextFrame();

  List<Integer> getScoringFramesIndexes();

  Score getAdditionalScore();

  int getSizeOfScoringFramesIndexes();

  FrameStatus createNextStatusBy(KnockedDownPins pins);

  boolean isOver();

  boolean isBonus();

  String getResultBy(KnockedDownPins pins);

  boolean isFinished();
}
