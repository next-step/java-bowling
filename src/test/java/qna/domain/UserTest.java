package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
  public static final User JAVAJIGI =
      new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
  public static final User SANJIGI =
      new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

  @Test
  void name() {
    assertThat(JAVAJIGI)
        .isEqualTo(new User(1L, "nextjigi", "비밀번호", "next", "nextjigi@slipp.net"));
  }
}
