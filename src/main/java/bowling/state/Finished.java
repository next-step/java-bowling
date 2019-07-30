package bowling.state;

import bowling.Pins;

abstract class Finished implements State {

  @Override
  public State bowl(Pins pins) {
    throw new RuntimeException("해당 프레임은 완료");
  }

  @Override
  public boolean isFinish() {
    return Boolean.TRUE;
  }
}
