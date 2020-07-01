package bowling.model;

public enum Symbols {
  STRIKE("X"),
  SPARE("/"),
  GUTTER("-"),
  SPACE(" "),
  BLANK(""),
  BAR("|");
  private final String value;

  Symbols(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
