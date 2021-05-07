package bowling.domain.state;

public class Strike extends State {

  private static final String STRIKE_MARK = "X";

  public Strike() {
    super(10, STRIKE_MARK);
  }
}
