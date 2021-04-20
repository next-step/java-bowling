package bowling.domain;

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

  public int countUptoSecondThrow() {
    return first().pin() + second().pin();
  }

  public int size() {
    return pins.size();
  }

  public Pin first() {
    return pins.get(FIRST);
  }

  public Pin second() {
    return pins.get(SECOND);
  }

  public Pin bonus() {
    return pins.get(BONUS);
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }
}
