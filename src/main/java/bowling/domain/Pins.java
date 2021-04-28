package bowling.domain;

public class Pins {

  private static final int MIN = 0;
  public static final int MAX = 10;

  private int totalPins;
  private int hitPins;

  public Pins(int totalPins, int hitPins) {
    this.totalPins = totalPins;
    this.hitPins = hitPins;
    if (hitPins < MIN || hitPins > totalPins) {
      throw new IllegalArgumentException("쓰러진핀의 갯수는 0이상 남은 핀이하 여야한다.");
    }
  }

  public int getLeftCount() {
    return totalPins - hitPins;
  }

  public boolean isClear() {
    return getLeftCount() == 0;
  }

  public Pins(int hitPins) {
    this(MAX, hitPins);
  }

  public int getHitPins() {
    return hitPins;
  }

  public boolean isGutter() {
    return hitPins == 0;
  }

}
