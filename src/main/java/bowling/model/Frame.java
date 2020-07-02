package bowling.model;

import bowling.model.framestatus.FrameStatus;
import java.util.List;

public interface Frame {

  void roll(int KnockDownNumber) throws FrameOverException;

  Frame next();

  Score getScore();

  int getFirstKnockDownNumber();

  boolean isOver();

  boolean isFinished();

  int getSizeOfScoringFramesIndexes();

  KnockedDownPins getPins();

  FrameStatus getFrameStatus();

}
