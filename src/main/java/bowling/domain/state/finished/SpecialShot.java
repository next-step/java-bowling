package bowling.domain.state.finished;

import bowling.domain.Score;
import bowling.domain.turn.FallenPins;

public class SpecialShot extends Finished {

  private final FallenPins fallenPins;

  private SpecialShot(FallenPins pins) {
    this.fallenPins = pins;
  }

  public static SpecialShot of(FallenPins pins) {
    return new SpecialShot(pins);
  }

  @Override
  public String show() {
    return fallenPins.show();
  }

  @Override
  public Score calculateScore() {
    return Score.ofSpecialShot(fallenPins);
  }

  @Override
  public Score addScore(Score score) {
    if (score.canCalculateScore()) {
      return score;
    }

    return score.bowl(fallenPins.pins());
  }
}
