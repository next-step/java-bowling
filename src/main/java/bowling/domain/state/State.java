package bowling.domain.state;

public interface State {

  State play(int pinCount);
}
