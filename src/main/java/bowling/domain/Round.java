package bowling.domain;

public class Round {
  public static int FINAL_ROUND = 10;
  public static int ROUND_UNIT = 1;
  private static int FIRST_ROUND = 1;

  private int round;

  private Round(int round) {
    if (round < FIRST_ROUND || round > FINAL_ROUND) {
      throw new IllegalArgumentException("최소 1라운드에서 최대 10라운드까지 가능합니다.");
    }

    this.round = round;
  }

  public static Round of(int round) {
    return new Round(round);
  }

  public static Round first() {
    return new Round(FIRST_ROUND);
  }

  public boolean isFinal() {
    return round == FINAL_ROUND;
  }

  public Round next() {
    if (isFinal()) {
      throw new IllegalArgumentException(String.format("최대 %d라운드까지 플레이할 수 있습니다.", round));
    }

    return Round.of(round + ROUND_UNIT);
  }
}
