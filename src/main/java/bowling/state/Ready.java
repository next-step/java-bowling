package bowling.state;

import bowling.Pins;
import bowling.Score;

public class Ready extends Running {

  @Override
  public State bowl(Pins pins) {
    if (pins.isStrike()) {
      return new Strike();
    }
    return new FirstBowl(pins);
  }

  @Override
  public String desc() {
    return "";
  }

  @Override
  public Score addAdditionalScore(Score prevScore) {
    return Score.noFinishScore();
  }

}
