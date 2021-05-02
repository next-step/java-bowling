package bowling.domain;

public class Player {

  public static final int MAX_LENGTH = 3;
  private String name;

  public Player(String name) {
    if (name.length() != MAX_LENGTH) {
      throw new IllegalArgumentException("이름은 3자 여야한다.");
    }
    if (!name.matches("^[a-zA-Z]*$")) {
      throw new IllegalArgumentException("이름은 영어로 이뤄져야한다.");
    }
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
