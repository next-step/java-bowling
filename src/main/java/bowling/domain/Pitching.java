package bowling.domain;

public class Pitching {

  private static final int MAX_TRY = 2;

  private Pins pins;
  private int tryCount = 0;

  public Pitching(Pins pins, int tryCount) {
    this.pins = pins;
    this.tryCount = tryCount;
  }

  public Pitching() {
    this.pins = new Pins();
  }

  public Pitching play(int hitCount) {
    Pins hitPins = pins.hit(hitCount);
    return new Pitching(hitPins, tryCount + 1);
  }

  public int leftPins() {
    return pins.count();
  }

  public Result result() {
    if (tryCount == 0) {
      return Result.NONE;
    }
    if (tryCount == 1 && isClear()) {
      return Result.STRIKE;
    }
    if (tryCount == 2 && isClear()) {
      return Result.SPARE;
    }
    return Result.MISS;
  }

  public boolean isClear() {
    return pins.count() == 0;
  }

  public boolean isEnd() {
    return result().isStrike() || tryCount == MAX_TRY;
  }

}
