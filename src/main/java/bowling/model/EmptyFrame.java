package bowling.model;

import bowling.model.framestatus.FrameStatus;
import java.util.List;

public class EmptyFrame implements Frame {

  @Override
  public void roll(int KnockDownNumber) throws FrameOverException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Frame next() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Score getScore() {
    return new Score(0);
  }

  @Override
  public int getFirstKnockDownNumber() {
    return 0;
  }

  @Override
  public boolean isOver() {
    return true;
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public int getSizeOfScoringFramesIndexes() {
    return 0;
  }

  @Override
  public KnockedDownPins getPins() {
    throw new UnsupportedOperationException();
  }

  @Override
  public FrameStatus getFrameStatus() {
    throw new UnsupportedOperationException();
  }

  public Score getScoreBy(List<Frame> frames) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "EmptyFrame{}";
  }

  @Override
  public boolean equals(Object obj) {
    return this.getClass().equals(obj.getClass());
  }
}
