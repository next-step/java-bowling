package bowling.domain.state;

public class State {

  private int score;
  private String expression;

  public State(int score, String expression) {
    this.score = score;
    this.expression = expression;
  }

  public static State strike() {
    return new Strike();
  }

  public static State gutter() {
    return new Gutter();
  }

  public static State spare(int score) {
    return new Spare(score);
  }

  public static State miss(int score) {
    return new Miss(score);
  }

  public String getScore() {
    if (expression.isEmpty()) {
      return String.valueOf(score);
    }
    return expression;
  }
}
