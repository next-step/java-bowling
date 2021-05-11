package bowling.domain.state.running;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.error.CannotCalculateException;

public abstract class Running implements State {

  @Override
  public boolean isFinished() {
    return false;
  }


  @Override
  public Score calculateScore() {
    throw new CannotCalculateException();
  }

}
