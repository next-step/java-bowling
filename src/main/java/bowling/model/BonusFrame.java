package bowling.model;

import bowling.model.framestatus.Bonus;
import bowling.model.framestatus.FrameStatus;
import java.util.List;
import java.util.Objects;

public class BonusFrame implements Frame {

  private KnockDownNumber knockDownNumber = new KnockDownNumber();
  private Frame nextFrame = new EmptyFrame();
  private boolean hasNext;

  public BonusFrame(boolean hasNext) {
    this.hasNext = hasNext;
    if (hasNext) {
      nextFrame = new BonusFrame(false);
    }
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
    return nextFrame;
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
    return nextFrame.isOver();
  }

  @Override
  public boolean isFinished() {
    return !hasNext;
  }

  @Override
  public int getSizeOfScoringFramesIndexes() {
    return 0;
  }

  @Override
  public KnockedDownPins getPins() {
    return KnockedDownPins.getBuilder()
        .firstKnockDownNumber(knockDownNumber.getIntValue())
        .build();
  }

  @Override
  public FrameStatus getFrameStatus() {
    return new Bonus();
  }

  private Score getScoreBy(List<Frame> frames) {
    return new Score(0);
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
    return Objects.equals(knockDownNumber, that.knockDownNumber) &&
        Objects.equals(nextFrame, that.nextFrame);
  }

  @Override
  public int hashCode() {
    return Objects.hash(knockDownNumber, nextFrame);
  }

  @Override
  public String toString() {
    return "BonusFrame{" +
        "knockDownNumber=" + knockDownNumber +
        ", nextFrame=" + nextFrame +
        '}';
  }
}
