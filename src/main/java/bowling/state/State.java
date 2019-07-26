package bowling.state;

public interface State {

  State roll(int countOfPin);

  Boolean isFinish();

}
