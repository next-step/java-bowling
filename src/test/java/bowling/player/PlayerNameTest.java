package bowling.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

  @DisplayName("영어 글자3개를 넣으면 이름객체가 생성된다.")
  @ParameterizedTest
  @ValueSource(strings = {"kdh", "KDH"})
  void create(String names) {
    assertThat(new PlayerName(names)).isEqualTo(new PlayerName(names));
  }

  @DisplayName("입력되는 글자가 영어 3어글가 아닐때 검증을 확인한다.")
  @Test
  void validationName() {
    assertAll(
        () -> assertThatThrownBy(() -> new PlayerName("abcd"))
            .isInstanceOf(IllegalArgumentException.class),

        () -> assertThatThrownBy(() -> new PlayerName("가1!"))
            .isInstanceOf(IllegalArgumentException.class)
    );
  }
}