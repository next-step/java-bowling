package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

  public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name",
      "javajigi@slipp.net");
  public static final User SANJIGI = new User(2L, "sanjigi", "password", "name",
      "sanjigi@slipp.net");

  @Test
  @DisplayName("유저생성")
  void createUser() {
    assertThat(new User(1L, "javajigi", "password", "name",
        "javajigi@slipp.net")).isEqualTo(JAVAJIGI);

  }
}
