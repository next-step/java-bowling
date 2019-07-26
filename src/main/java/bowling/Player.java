package bowling;

import java.util.Objects;

public class Player {

  private static final int MAX_NAME_LENGTH = 3;
  private String name;

  public Player(String name) {
    this.name = name;
  }

  public static Player of(String name) {
    if (isBlank(name)) {
      throw new IllegalArgumentException("이름은 빈값일 수 없습니다.");
    }
    if (name.length() > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException("이름은 3글자 까지만 가능합니다.");
    }
    return new Player(name);
  }

  private static boolean isBlank(String name) {
    return name == null || "".equals(name.trim());
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return Objects.equals(name, player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
