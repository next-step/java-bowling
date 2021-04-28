package bowling.domain;

public class Pitching {

  private Pins pins;
  private int tryCount;

  private Pitching(Pins pins, int tryCount) {
    this.pins = pins;
    this.tryCount = tryCount;
  }

  public static Pitching firstPitching(int hitCount) {
    return new Pitching(new Pins(hitCount), 1);
  }

  public Pitching secondPitching(int hitCount) {
    int leftPins = pins.getLeftCount();
    return new Pitching(new Pins(leftPins, hitCount), 2);
  }

  public int getHitPins() {
    return pins.getHitPins();
  }

  public Result getResult() {
    if (pins.isGutter()) {
      return Result.GUTTER;
    }

    if (pins.isClear()) {
      if (tryCount == 1) {
        return Result.STRIKE;
      }
      return Result.SPARE;
    }

    return Result.MISS;
  }

}
