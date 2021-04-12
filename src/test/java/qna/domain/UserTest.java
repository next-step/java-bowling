package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

public class UserTest {

  public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name",
      "javajigi@slipp.net");
  public static final User SANJIGI = new User(2L, "sanjigi", "password", "name",
      "sanjigi@slipp.net");

  @Test
  @DisplayName("유저 업데이트")
  public void update() {
    JAVAJIGI.update(JAVAJIGI, SANJIGI);
    assertThat(JAVAJIGI.equalsNameAndEmail(SANJIGI)).isTrue();
  }

  @Test
  @DisplayName("유저 아이디 일치하는지 확인")
  public void matchUserId() {
    assertThat(JAVAJIGI.matchUserId("javajigi")).isTrue();
  }

  @Test
  @DisplayName("유저 비밀번호 일치하는지 확인")
  public void matchPassword() {
    assertThat(JAVAJIGI.matchPassword("password")).isTrue();
  }

  @Test
  @DisplayName("유저 이름과 이메일 일치하는지 확인")
  public void equalsNameAndEmail() {
    assertThat(JAVAJIGI.equalsNameAndEmail(JAVAJIGI)).isTrue();
  }

  @Test
  @DisplayName("게스트 유저인지 확인")
  public void isGuestUser() {
    assertThat(JAVAJIGI.isGuestUser()).isFalse();
  }

}
