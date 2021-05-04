package bowling.domain;

public enum Result {
  STRIKE("X"),
  SPARE("/"),
  MISS(""),
  NONE(""),
  GUTTER("-");

  private String mark;

  Result(String mark) {
    this.mark = mark;
  }

  public String mark() {
    return mark;
  }

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
