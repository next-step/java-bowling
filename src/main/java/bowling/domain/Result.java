package bowling.domain;

public enum Result {
  STRIKE("X"),
  SPARE("/"),
  GUTTER("-"),
  MISS(""),
  NONE("")
  ;

  private String mark;

  Result(String mark) {
    this.mark = mark;
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

  public String getMark() {
    return mark;
  }

  public boolean isNotNone() {
    return !this.equals(NONE);
  }

}
