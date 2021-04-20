package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {

  private static final int MAX_NAME_LENGTH = 3;
  private static final String REGEX = "^[a-zA-Z]*$";
  private final String player;

  public Player(String player) {
    validateName(player);
    this.player = player;
  }

  private void validateName(String player) {
    if (player.length() > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException("이름은 3자를 넘을 수 없습니다.");
    }
    if (!Pattern.matches(REGEX, player)) {
      throw new IllegalArgumentException("이름은 영문만 입력할 수 있습니다.");
    }
  }

  public String name() {
    return player;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Player player1 = (Player) o;

    return Objects.equals(player, player1.player);
  }

  @Override
  public int hashCode() {
    return player != null ? player.hashCode() : 0;
  }
}
