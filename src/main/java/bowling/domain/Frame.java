package bowling.domain;

public abstract class Frame {

  private static final int FIRST_THROW = 1;
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

  public boolean isFirstThrow() {
    return pins.size() == FIRST_THROW;
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

  public Pins pins() {
    return pins;
  }

  public boolean isFinalRound() {
    return round.isFinalRound();
  }

  abstract public boolean isEndFrame();

  abstract public boolean isLastFrame();

  abstract public void validateHitPin(int countOfHitPin);
}
