package bowling;

public class Frame {

  private State state;

  public void roll(int pins) {
    if (state != null) {
      state = state.roll(pins);
    }

    if (state == null) {
      state = State.of(pins);
    }
  }

  public boolean isDone() {
    System.out.println(state);
    if (state instanceof Pitching) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Frame{" +
        "state=" + state +
        '}';
  }
}
