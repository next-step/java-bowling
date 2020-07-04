package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import bowling.model.EmptyFrame;

public class RequiredSecondRoll implements FrameStatus {

  private final int currentIndex;

  public RequiredSecondRoll(int currentIndex) {
    this.currentIndex = currentIndex;
  }

  @Override
  public Frame getNextFrame() {
    return new EmptyFrame();
  }

  @Override
  public Score getAdditionalScore() {
    return new Score(0);
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    if (pins.getRemainingNumber() == 0) {
      return new Spare(currentIndex);
    }

    return new Miss(currentIndex);
  }

  @Override
  public boolean isOver() {
    return false;
  }

  @Override
  public boolean isBonus() {
    return false;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    return String.valueOf(pins.getFirstKnockDownNumber())
        .replace("0", GUTTER.toString());
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public String toString() {
    return "RequiredSecondRoll{" +
        "currentIndex=" + currentIndex +
        '}';
  }
}
