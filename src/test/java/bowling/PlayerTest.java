package bowling;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

  @ParameterizedTest
  @ValueSource(strings = {"abcd", "bcds", "$@s"})
  @DisplayName("player 이름은 3개의 영문자 확인")
  public void create(String str) {
    assertThatThrownBy(() -> {
      Player player = new Player(str);
    }).isInstanceOf(IllegalArgumentException.class);
  }

}
