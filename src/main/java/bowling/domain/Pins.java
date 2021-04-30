package bowling.domain;

import bowling.error.InvalidFallenPinsException;

public class Pins {
  private static final int MIN_PINS = 0;
  private static final int MAX_PINS = 10;

  private final int pins;

  public Pins(int pins){
    checkPins(pins);
    this.pins = pins;
  }

  private void checkPins(int pins){
    if(pins< MIN_PINS || pins > MAX_PINS){
      throw new InvalidFallenPinsException();
    }
  }

  public Pins addingOtherPins(Pins pins){
    int checkingPins = this.pins + pins.pins;
    return new Pins(checkingPins);
  }

  public int pins(){
    return pins;
  }

}
