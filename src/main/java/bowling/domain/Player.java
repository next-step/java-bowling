package bowling.domain;

public class Player {
  private static final String NAME_REGEX = "^[a-zA-z]{3}$";

  private String name;

  public Player(String name) {
    validateName(name);

    this.name = name;
  }

  private void validateName(String name) {
    if (!name.matches(NAME_REGEX)) {
      throw new IllegalArgumentException("이름은 영문 3글자여야합니다.");
    }
  }

  public String getName() {
    return name;
  }
}
