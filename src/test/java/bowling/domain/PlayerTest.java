package bowling.domain;

import bowling.error.NameInvalidLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

  @Test
  @DisplayName("이름 생성 테스트")
  void createNameTest() {
    String name = "nan";

    assertThat(new Player(name).name()).isEqualTo(name);
  }

  @Test
  @DisplayName("이름은 무조건 3글자여야 한다.")
  void invalidCreateNameTest() {
    String name = "nani";

    assertThatThrownBy(() -> new Player(name)).isInstanceOf(NameInvalidLengthException.class);
  }

}