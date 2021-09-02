package bowling.player;

import java.util.Objects;
import java.util.regex.Pattern;

public class PlayerName {

  private static final int MAX_NAME_LENGTH = 3;
  private static final String MSG_ERROR_MAX_LENGTH = "이름은 3글자 까지만 가능합니다.";
  private static final String ENGLISH_PATTERN = "^[a-zA-Z]*$";
  private static final String MSG_ERROR_ONLY_ENGLISH = "영어만 입력 가능합니다.";

  private final String name;

  public PlayerName(final String name) {
    validationName(name);

    this.name = name;
  }

  private void validationName(final String name) {
    if (name.length() > MAX_NAME_LENGTH) {
      throw new IllegalArgumentException(MSG_ERROR_MAX_LENGTH);
    }

    if (!Pattern.matches(ENGLISH_PATTERN, name)) {
      throw new IllegalArgumentException(MSG_ERROR_ONLY_ENGLISH);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PlayerName name1 = (PlayerName) o;
    return Objects.equals(name, name1.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return name;
  }
}
