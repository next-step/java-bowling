package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NormalFrameTest {

  @Test
  void 생성() {
    NormalFrame normalFrame = NormalFrame.first();
  }

  @Test
  void 동치테스트() {
    assertThat(NormalFrame.first()).isEqualTo(NormalFrame.first());
  }

  @Test
  void 첫번째_시도에_10개를_쓰러뜨리면_다음프레임이_호출된다() {
    NormalFrame firstFrame = NormalFrame.first();
    assertThat(firstFrame.roll(10)).isEqualTo(new NormalFrame(2));
  }
}
