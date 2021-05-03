package bowling.domain;

public enum Result {
  STRIKE,SPARE,MISS,NONE;

  public boolean isNotMiss() {
    return isStrike() || isSpare();
  }
  public boolean isStrike() {
    return this.equals(STRIKE);
  }

  private boolean isSpare() {
    return this.equals(SPARE);
  }

  public boolean isNotNone() {
    return !this.equals(NONE);
  }

}
