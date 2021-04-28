package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

  @Test
  @DisplayName("[Name] 참가자 이름 생성 테스트")
  void create_name() {
    String name = "NHJ";
    Name player = new Name(name);
    assertThat(player).isEqualTo(new Name(name));
  }

  @Test
  @DisplayName("[Player] 이름의 길이가 3글자가 넘는 경우 예외 처리 테스트")
  void invalid_name_length() {
    String player = "crong";
    assertThatThrownBy(() -> new Name(player))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("[Player] 영문이 아닌 문자를 입력했을 경우 예외 처리 테스트")
  void invalid_name() {
    String player = "나나";
    assertThatThrownBy(() -> new Name(player))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
