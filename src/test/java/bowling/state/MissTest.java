package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MissTest {

  Miss miss;

  @BeforeEach
  void 생성() {
    miss = new Miss(new Pins(5), new Pins(3));
  }

  @Test
  void 미스_desc확인() {
    assertThat(miss.desc()).isEqualTo("5|3");
  }

  @Test
  void Miss는_프레임의_끝이다() {
    assertThat(miss.isFinish()).isTrue();
  }

  @Test
  void 한프레임에서_Miss후에는_투구할수없다() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(()->{
      miss.bowl(new Pins(5));
    });
  }
}