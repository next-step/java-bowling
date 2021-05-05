package bowling.domain.state.finished;

import bowling.domain.state.State;

public class Strike implements State {

  @Override
  public boolean isFinished() {
    return true;
  }
}
