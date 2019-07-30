package bowling.state;

public class Strike extends Finished {

  private static final String STRIKE_SYMBOL = "X";

  @Override
  public String desc() {
    return STRIKE_SYMBOL;
  }
}
