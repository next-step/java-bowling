package bowling.model;

import bowling.model.framestatus.FrameStatus;

public class EmptyFrame implements Frame {

  @Override
  public void roll(int KnockDownNumber) {
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

  public KnockedDownPins getPins() {
    throw new UnsupportedOperationException();
  }

  public FrameStatus getFrameStatus() {
    throw new UnsupportedOperationException();
  }

  @Override
  public FrameDTO createDTO() {
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
