package bowling.state;

public class Strike implements State {

  private static final String STRIKE_SYMBOL ="X";

  @Override
  public State roll(int countOfPin)  {
    throw new RuntimeException("해당프레임은 끝났습니다.");
  }

  @Override
  public Boolean isFinish() {
    return true;
  }

  @Override
  public String toString() {
    return STRIKE_SYMBOL;
  }
}
