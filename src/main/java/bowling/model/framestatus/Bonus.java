package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;

public class Bonus implements FrameStatus {

  @Override
  public Frame getNextFrame() {
    return null;
  }

  @Override
  public int getCurrentIndex() {
    return -1;
  }

  @Override
  public Score getAdditionalScore() {
    return new Score(0);
  }

  @Override
  public FrameStatus createNextStatusBy(KnockedDownPins pins) {
    return this;
  }

  @Override
  public boolean isOver() {
    return true;
  }

  @Override
  public boolean isBonus() {
    return true;
  }

  @Override
  public String getResultBy(KnockedDownPins pins) {
    if (pins.getFirstKnockDownNumber() == 10) {
      return STRIKE.toString();
    }

    return String.valueOf(pins.getFirstKnockDownNumber()).replaceAll("0", GUTTER.toString());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
