package bowling.state;

public class StateFactory {

  public static State startPitch() {
    return new FirstPitch();
  }
}