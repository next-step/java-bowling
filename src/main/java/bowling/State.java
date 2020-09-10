package bowling;

public interface State {

  int MIN_PINS = 0;
  int MAX_PINS = 10;

  static State of(Pins pins) {
    if (pins.isStrike()) {
      return new Strike();
    }

    return new Pitching(pins);
  }

  String symbol();

  State roll(Pins second);

}