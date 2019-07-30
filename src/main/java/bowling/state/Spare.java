package bowling.state;

import bowling.Pins;

public class Spare extends Finished {
  private static final String SPARE_SYMBOL = "/";

  private Pins firstPins;
  private Pins secondPins;

  public Spare(Pins firstPins, Pins secondPins) {
    this.firstPins = firstPins;
    this.secondPins = secondPins;
  }

  @Override
  public String desc() {
    return firstPins.desc()+RESULT_CONCAT_SYMBOL+SPARE_SYMBOL;
  }
}
