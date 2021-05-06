package bowling.domain.state;

public class Spare extends State{

  private static final String SPARE_MARK = "/";

  public Spare(int score) {
    super(score, SPARE_MARK);
  }
}
