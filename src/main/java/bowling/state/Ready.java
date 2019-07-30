package bowling.state;

import bowling.Pins;

public class Ready extends Running {

  @Override
  public State bowl(Pins pins) {
    if(pins.isStrike()) {
      return new Strike();
    }
    return new FirstBowl(pins);
  }

  @Override
  public String desc() {
    return "";
  }
}
