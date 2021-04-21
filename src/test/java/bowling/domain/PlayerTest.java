package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

  @ParameterizedTest
  @ValueSource(strings = {"abcd", "bcds", "$@s"})
  @DisplayName("player 이름은 3개의 영문자 확인")
  public void create(String str) {
    assertThatThrownBy(() -> {
      new Player(str);
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
