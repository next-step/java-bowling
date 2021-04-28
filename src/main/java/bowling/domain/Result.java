package bowling.domain;

public enum Result {
  SPARE("/"),
  STRIKE("X"),
  MISS(""),
  GUTTER("-"),
  NONE("");

  private String mark;

  Result(String mark) {
    this.mark = mark;
  }

  public String getMark() {
    return mark;
  }

  public boolean isStrike() { return this.equals(STRIKE); }

  public boolean isSpare() {
    return this.equals(SPARE);
  }

  public boolean isNotMiss() {
    return isStrike() || isSpare();
  }

}
