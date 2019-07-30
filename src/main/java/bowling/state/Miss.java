package bowling.state;

import bowling.Pins;
import java.util.Objects;

public class Miss extends Finished {

  private Pins firstPins;
  private Pins secondPins;

  public Miss(Pins firstPins, Pins secondPins) {
    this.firstPins = firstPins;
    this.secondPins = secondPins;
  }

  @Override
  public String desc() {
    return firstPins.desc() + RESULT_CONCAT_SYMBOL + secondPins.desc();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Miss miss = (Miss) o;
    return Objects.equals(firstPins, miss.firstPins) &&
        Objects.equals(secondPins, miss.secondPins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstPins, secondPins);
  }
}
