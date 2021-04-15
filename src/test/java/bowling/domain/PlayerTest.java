package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  @DisplayName("[Player] 참가자 객체 생성 테스트")
  void create_player() {
    String name = "NHJ";
    Player player = new Player(name);
    assertThat(player).isEqualTo(new Player(name));
  }

  @Test
  @DisplayName("[Player] 이름의 길이가 3글자가 넘는 경우 예외 처리 테스트")
  void invalid_name_length() {
    String player = "crong";
    assertThatThrownBy(() -> new Player(player))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("[Player] 영문이 아닌 문자를 입력했을 경우 예외 처리 테스트")
  void invalid_name() {
    String player = "나나";
    assertThatThrownBy(() -> new Player(player))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
