package bowlingstate.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {

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
