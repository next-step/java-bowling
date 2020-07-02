package bowling.model;

import bowling.model.framestatus.Bonus;
import bowling.model.framestatus.FrameStatus;
import java.util.List;

public class BonusFrame implements Frame {

  private KnockDownNumber knockDownNumber = new KnockDownNumber();

  @Override
  public void roll(int KnockDownNumber) throws FrameOverException {
    if (!knockDownNumber.isNull()) {
      throw new FrameOverException();
    }

    knockDownNumber = new KnockDownNumber(KnockDownNumber);
  }

  @Override
  public boolean isOver() {
    return true;
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

  @Override
  public Score getScoreBy(List<Frame> frames) {
    return new Score(0);
  }

  @Override
  public String toString() {
    return "BonusFrame{" +
        "knockDownNumber=" + knockDownNumber +
        '}';
  }
}
