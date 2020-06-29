package bowling.model;

import java.util.Collections;
import java.util.List;

public class BonusFrame implements Frame {

  private KnockDownNumber knockDownNumber = new KnockDownNumber();

  @Override
  public void roll(int knockDownNum) throws FrameOverException {
    if (!knockDownNumber.isNull()) {
      throw new FrameOverException();
    }

    knockDownNumber = new KnockDownNumber(knockDownNum);
  }

  @Override
  public boolean isOver() {
    return true;
  }

  @Override
  public int getRemainingPinsNum() {
    return KnockedDownPins.MAX_NUMBER_OF_PINS - knockDownNumber.getIntValue();
  }

  @Override
  public List<Integer> getIndexOfScoredFrames() {
    return Collections.emptyList();
  }

  @Override
  public KnockedDownPins getPins() {
    return KnockedDownPins.getBuilder(knockDownNumber.getIntValue()).build();
  }

  @Override
  public FrameStatus getFrameStatus() {
    return new Bonus();
  }

  @Override
  public String toString() {
    return "BonusFrame{" +
        "knockDownNumber=" + knockDownNumber +
        '}';
  }
}
