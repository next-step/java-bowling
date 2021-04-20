package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
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

  public List<Pin> pins() {
    return Collections.unmodifiableList(pins);
  }

  public Pin get(int index) {
    return pins.get(index);
  }

}
