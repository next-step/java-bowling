package bowling.domain;

import java.util.List;

public abstract class Frame {

  private static final int NORMAL_FRAME_MAX_SIZE = 2;
  protected final Pins pins;
  protected Round round;

  public Frame(Round round) {
    this.round = round;
    pins = new Pins();
  }

  public void play(int countOfHitPin) {
    validateHitPin(countOfHitPin);
    pins.add(new Pin(countOfHitPin));
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

  public boolean isFinalRound() {
    return round.isFinalRound();
  }

  public List<String> frameState() {
    return pins.frameState();
  }

  protected ScoreSymbol symbol() {
    return ScoreSymbol.symbol(pins.totalHitPin(), pins.size() < NORMAL_FRAME_MAX_SIZE);
  }

  abstract public boolean isEndFrame();

  abstract public boolean isLastFrame();

  abstract protected void validateHitPin(int countOfHitPin);
}
