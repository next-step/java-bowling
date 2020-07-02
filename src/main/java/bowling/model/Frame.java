package bowling.model;

import bowling.model.framestatus.FrameStatus;
import java.util.List;

public interface Frame {

  void roll(int KnockDownNumber) throws FrameOverException;

  boolean isOver();

  int getSizeOfScoringFramesIndexes();

  KnockedDownPins getPins();

  FrameStatus getFrameStatus();

  int getScoreBy(List<Frame> frames);
}
