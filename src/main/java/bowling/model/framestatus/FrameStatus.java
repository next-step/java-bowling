package bowling.model.framestatus;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;

public interface FrameStatus {

  Frame getNextFrame();

  int getCurrentIndex();

  Score getAdditionalScore();

  FrameStatus createNextStatusBy(KnockedDownPins pins);

  boolean isOver();

  boolean isBonus();

  String getResultBy(KnockedDownPins pins);

  boolean isFinished();
}
