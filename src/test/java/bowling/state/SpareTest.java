package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpareTest {

  State state;

  @BeforeEach
  void 생성() {
    state = new Spare(new Pins(5), new Pins(5));
  }

  @Test
  void 스페어_desc확인() {
    assertThat(state.desc()).isEqualTo("5|/");
  }

  @Test
  void 스페어는_프레임의_끝이다() {
    assertThat(state.isFinish()).isTrue();
  }

  @Test
  void 한프레임에서_Miss후에는_투구할수없다() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(()->{
      state.bowl(new Pins(5));
    });
  }

}