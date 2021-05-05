package bowling.domain.frame;

import bowling.domain.turn.FallenPins;

import java.util.List;

public class FinalFrame extends Frame {

  protected FinalFrame(int round) {
    super(round);
  }

  @Override
  public List<FallenPins> shot(FallenPins fallenPins) {
    checkThrowable(fallenPins);
    this.fallenPins.add(fallenPins);
    return this.fallenPins;
  }

  @Override
  protected void checkThrowable(FallenPins pins) {
    if (fallenPins.size() <= MAX_THROWABLE_BALLS && fallenPinsStatus() == MAX_FALLEN_PINS) {
      return;
    }
    super.checkThrowable(pins);
  }

  @Override
  public boolean checkFinished() {
    if (super.isStrike() || super.isSpare()) {
      return false;
    }

    return fallenPins.size() >= MAX_THROWABLE_BALLS || fallenPinsStatus() >= MAX_FALLEN_PINS;
  }

  @Override
  public int round() {
    return 10;
  }

  @Override
  public String show() {
    return "";
  }

  @Override
  public boolean isStrike() {
    if (fallenPins.size() >= STRIKE_SIZE) {
      return head().isStrike();
    }
    return false;
  }

  @Override
  public boolean isSpare() {
    if (fallenPins.size() >= MAX_THROWABLE_BALLS) {
      return calculateFirstAndSecondShot(fallenPins) == MAX_FALLEN_PINS;
    }
    return super.isSpare();
  }

  private int calculateFirstAndSecondShot(List<FallenPins> fallenPins) {
    return fallenPins.get(0).pins() + fallenPins.get(1).pins();
  }
}
