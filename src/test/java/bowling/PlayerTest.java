package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

  @Test
  void 생성() {
    Player player = Player.of("LCJ");
  }

  @Test
  void 동치테스트() {
    assertThat(Player.of("LCJ")).isEqualTo(Player.of("LCJ"));
  }

  @Test
  void 이름은_세글자를_넘어갈수_없습니다() {
    assertThatIllegalArgumentException().isThrownBy(() ->{
      Player.of("LCJJ");
    });
  }

  @ParameterizedTest
  @ValueSource(strings = {""," "})
  @NullSource
  void 이름은_공백이나_null일수_없습니다(String name) {
    assertThatIllegalArgumentException().isThrownBy(() ->{
      Player.of(name);
    });
  }

}
