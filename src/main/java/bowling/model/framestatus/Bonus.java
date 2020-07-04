package bowling.model.framestatus;

import static bowling.model.Symbols.*;

import bowling.model.BonusFrame;
import bowling.model.EmptyFrame;
import bowling.model.Frame;
import bowling.model.KnockedDownPins;
import bowling.model.Score;
import java.util.Objects;

public class Bonus implements FrameStatus {

  private Frame nextFrame;
  private boolean finished;

  public Bonus() {
  }

  private Bonus(Frame frame, boolean finished) {
    this.nextFrame = frame;
    this.finished = finished;
  }

  public static Bonus createHasNext() {
    return new Bonus(new BonusFrame(Bonus.createHasFinished()), false);
  }

  public static Bonus createHasFinished() {
    return new Bonus(new EmptyFrame(), true);
  }


  @Override
  public Frame getNextFrame() {
    return nextFrame;
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
    return finished;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bonus bonus = (Bonus) o;
    return finished == bonus.finished &&
        nextFrame.equals(bonus.nextFrame);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nextFrame, finished);
  }

  @Override
  public String toString() {
    return "Bonus{" +
        "nextFrame=" + nextFrame +
        ", finished=" + finished +
        '}';
  }
}
