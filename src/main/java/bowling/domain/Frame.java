package bowling.domain;

public class Frame {

  private Pins first;
  private Pins second;
  private Result result = Result.NONE;

  public Frame() {}

  public void play(int hitCount) {
    if (first != null && second == null) {
      playSecondBall(hitCount);
    }

    if (first == null) {
      playFirst(hitCount);
    }
  }

  private void playFirst(int hitCount) {
    this.first = new Pins(hitCount);
    if (first.isClear()) {
      this.result = Result.STRIKE;
    }
  }

  private void playSecondBall(int hitCount) {
    if (this.result.isStrike()) {
      return;
    }

    this.second = new Pins(first.getLeftCount(), hitCount);
    if (second.isClear()) {
      this.result = Result.SPARE;
    }
  }

  public int getFirstHit() {
    return first.getHitPins();
  }

  public int getSecondHit() {
    return second.getHitPins();
  }

  private int getTotalHit() {
    if (second == null) {
      return getFirstHit();
    }
    return getFirstHit() + getSecondHit();
  }

  public boolean isStrike() {
    return result.isStrike();
  }

  public boolean isSpare() {
    return result.isSpare();
  }

  public int getBonusBeforeFrame(Result result) {
    if (result.isStrike()) {
      return getTotalHit();
    }
    return first.getHitPins();
  }

  public boolean isEnd() {
    if (result.isNotNone()) {
      return true;
    }
    return first != null && second != null;
  }

  @Override
  public String toString() {
    return "Frame{" +
        "first=" + first +
        ", second=" + second +
        ", result=" + result +
        '}';
  }
}
