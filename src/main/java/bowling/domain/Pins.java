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

  public int countUptoSecondPin() {
    return first().pin() + second().pin();
  }

  public int size() {
    return pins.size();
  }

  private Pin first() {
    return pins.get(FIRST);
  }

  private Pin second() {
    return pins.get(SECOND);
  }

  private Pin bonus() {
    return pins.get(BONUS);
  }

  public boolean isEmpty() {
    return pins.isEmpty();
  }

  public List<String> frameState() {
    States states = new States();
    states.add(state(first()));
    if (pins.size() == SECOND + 1) {
      states.add(nextState());
    }
    if (pins.size() == BONUS + 1) {
      states.add(nextState());
      states.add(state(bonus()));
    }
    return states.states();
  }

  private String state(Pin pin) {
    ScoreSymbol scoreSymbol = ScoreSymbol.symbol(pin.pin(), true);
    if (scoreSymbol == ScoreSymbol.STRIKE || scoreSymbol == ScoreSymbol.GUTTER) {
      return scoreSymbol.mark();
    }
    return pin.convertToString();
  }

  private String nextState() {
    ScoreSymbol scoreSymbol = ScoreSymbol.symbol(countUptoSecondPin(), false);
    if (scoreSymbol == ScoreSymbol.SPARE) {
      return scoreSymbol.mark();
    }
    return state(second());
  }
}
