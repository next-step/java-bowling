package bowling.domain;

public abstract class Frame {

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

  public String frameSymbol() {
    return pins.frameState();
  }

  abstract public boolean isEndFrame();

  abstract public boolean isLastFrame();

  abstract protected void validateHitPin(int countOfHitPin);
}
