package bowling.domain;

public class Round {

  private static final int FIRST_ROUND = 1;
  private static final int FINAL_ROUND = 10;
  private final int round;

  public Round(int round) {
    this.round = round;
  }

  public static Round firstRound() {
    return new Round(FIRST_ROUND);
  }

  public Round next() {
    return new Round(this.round + 1);
  }

  public int round() {
    return round;
  }

  public boolean isFinalRound() {
    return round == FINAL_ROUND;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Round round1 = (Round) o;

    return round == round1.round;
  }

  @Override
  public int hashCode() {
    return round;
  }
}
