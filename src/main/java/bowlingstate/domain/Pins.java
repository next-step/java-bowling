package bowlingstate.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {

  private static final int FIRST = 0;
  private static final int SECOND = 1;
  private static final int BONUS = 2;
  private final List<Pin> pins;

  public Pins(List<Pin> pins) {
    this.pins = pins;
  }

  public Pins() {
    this.pins = new ArrayList<>();
  }

  public void add(Pin pin) {
    pins.add(pin);
  }

  public int totalHitPin() {
    return pins.stream()
        .mapToInt(Pin::pin)
        .sum();
  }

  public int size() {
    return pins.size();
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

}
