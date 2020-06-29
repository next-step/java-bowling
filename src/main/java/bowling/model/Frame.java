package bowling.model;

import bowling.model.framestatus.FrameStatus;
import java.util.List;

public interface Frame {

  void roll(int KnockDownNum) throws FrameOverException;

  boolean isOver();

  int getRemainingPinsNum();

  List<Integer> getIndexOfScoredFrames();

  KnockedDownPins getPins();

  FrameStatus getFrameStatus();
}
