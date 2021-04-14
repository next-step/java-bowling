package bowling.domain;

import java.util.regex.Pattern;

public class Player {

  private final String name;
  private final static String INVALID_PATTERN_REGEX = "^[a-zA-Z]*$";
  private final static Pattern pattern = Pattern.compile(INVALID_PATTERN_REGEX);
  private final static String INVALID_NAME = "잘못된 이름입니다.";

  public Player(String name) {
    validateName(name);
    this.name = name;
  }

  private void validateName(String name) {
    if (name.length() != 3 || !pattern.matcher(name).matches()) {
      throw new IllegalArgumentException(INVALID_NAME);
    }
  }

}
