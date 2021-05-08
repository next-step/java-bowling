package bowling.domain.state.finished;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

public class SpecialShot implements State {

  private final FallenPins fallenPins;

  private SpecialShot(FallenPins pins){
    this.fallenPins = pins;
  }

  public static SpecialShot of(FallenPins pins){
    return new SpecialShot(pins);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public State bowl(FallenPins pins) {
    throw new CannotThrowBallException();
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
    if(score.canCalculateScore()){
      return score;
    }

    return score.bowl(fallenPins.pins());
  }
}
