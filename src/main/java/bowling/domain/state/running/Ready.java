package bowling.domain.state.running;

import bowling.domain.state.State;

public class Ready implements State {


  @Override
  public boolean isFinished() {
    return false;
  }
}
