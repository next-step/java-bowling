package bowling;

public interface State {

  State roll(int countOfPin);

  Boolean isFinish();

}
