package bowling.domain.state;

public class Hit implements  State{

  public Hit(int pinCount) {

  }

  @Override
  public State play(int pinCount) {
    return null;
  }
}
