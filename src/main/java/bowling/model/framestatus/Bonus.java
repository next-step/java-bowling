package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.BonusFrame;
import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import java.util.Collections;
import java.util.List;

public class Bonus implements FrameStatus {

  @Override
  public Frame getNextFrame() {
    return null;
  }

  @Override
  public List<Integer> getScoringFramesIndexes() {
    return Collections.emptyList();
  }

  @Override
  public Score getAdditionalScore() {
    return new Score(0);
  }

  @Override
  public int getSizeOfScoringFramesIndexes() {
    return 0;
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
