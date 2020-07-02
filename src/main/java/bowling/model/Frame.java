package bowling.model;

import bowling.model.framestatus.FrameStatus;

public interface Frame {

  void roll(int KnockDownNumber) throws FrameOverException;

  Frame next();

  Score getScore();

  int getFirstKnockDownNumber();

  boolean isOver();

  boolean isFinished();

  KnockedDownPins getPins();

  FrameStatus getFrameStatus();

}
