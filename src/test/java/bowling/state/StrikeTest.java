package bowling.state;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StrikeTest {

  State state;

  @BeforeEach
  void 생성() {
    state = new Strike();
  }

  @Test
  void 스트라이크는_프레임의_끝이다() {
    assertThat(state.isFinish()).isTrue();
  }

  @Test
  void 한프레임에서_스트라이크후에는_투구할수없다() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(()->{
      state.bowl(new Pins(5));
    });
  }

  @Test
  void 스트라이크_desc확인() {
    assertThat(new Strike().desc()).isEqualTo("X");
  }
}