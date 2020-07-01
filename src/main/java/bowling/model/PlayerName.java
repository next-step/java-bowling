package bowling.model;

import java.util.regex.Pattern;

public class PlayerName {

  private final static Pattern PARTTERN_CHECK_ENG = Pattern.compile("([A-Z]|[a-z]){1,3}");

  private final String value;

  public PlayerName(String value) {
    if (!PARTTERN_CHECK_ENG.matcher(value).matches()) {
      throw new IllegalArgumentException("이름은 영어 3글자를 입력해야 합니다.");
    }

    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
