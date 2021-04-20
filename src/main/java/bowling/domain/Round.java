package bowling.domain;

public class Round {

  private final int round;

  public Round(int round) {
    this.round = round;
  }

  public static Round firstRound() {
    return new Round(1);
  }

  public Round next() {
    return new Round(this.round + 1);
  }

  public int round() {
    return round;
  }

}
