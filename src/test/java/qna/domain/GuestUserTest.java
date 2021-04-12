package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GuestUserTest {

  @Test
  @DisplayName("게스트 유저인지 확인")
  public void isGuestUser() {
    assertThat(new GuestUser().isGuestUser()).isTrue();
  }
}
