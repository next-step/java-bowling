package bowling.model;

import bowling.model.framestatus.FrameStatus;
import java.util.Objects;

public class BonusFrame implements Frame {

  private KnockDownNumber knockDownNumber = new KnockDownNumber();
  private final FrameStatus frameStatus;

  public BonusFrame(FrameStatus frameStatus) {
    this.frameStatus = frameStatus;
  }

  @Override
  public void roll(int KnockDownNumber) throws FrameOverException {
    if (!knockDownNumber.isNull()) {
      throw new FrameOverException();
    }

    knockDownNumber = new KnockDownNumber(KnockDownNumber);
  }

  @Override
  public Frame next() {
    return frameStatus.getNextFrame();
  }

  @Override
  public Score getScore() {
    return new Score(0);
  }

  @Override
  public int getFirstKnockDownNumber() {
    return knockDownNumber.getIntValue();
  }

  @Override
  public boolean isOver() {
    return frameStatus.isOver();
  }

  @Override
  public boolean isFinished() {
    return frameStatus.isFinished();
  }

  public KnockedDownPins getPins() {
    return KnockedDownPins.getBuilder()
        .firstKnockDownNumber(knockDownNumber.getIntValue())
        .build();
  }

  @Override
  public FrameDTO createDTO() {
    KnockedDownPins pins = KnockedDownPins.getBuilder()
        .firstKnockDownNumber(knockDownNumber.getIntValue())
        .build();

    return new FrameDTO(pins, frameStatus);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BonusFrame that = (BonusFrame) o;
    return knockDownNumber.equals(that.knockDownNumber) &&
        frameStatus.equals(that.frameStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(knockDownNumber, frameStatus);
  }

  @Override
  public String toString() {
    return "BonusFrame{" +
        "knockDownNumber=" + knockDownNumber +
        ", frameStatus=" + frameStatus +
        '}';
  }
}
