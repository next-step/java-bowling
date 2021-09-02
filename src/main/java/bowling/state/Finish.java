package bowling.state;

public abstract class Finish implements State {

  @Override
  public State nextPitch(final int fallenPin) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isFinish() {
    return true;
  }
}
