package bowling.domain.state;

public class Miss extends State {

  public Miss(int score) {
    super(score, String.valueOf(score));
  }
}
