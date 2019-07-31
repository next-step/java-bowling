package bowling;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrameResultTest {

  FrameResult frameResult;

  @BeforeEach
  void 생성() {
    frameResult = new FrameResult("3|5", 8);
  }

  @Test
  void 동치테스트() {
    assertThat(new FrameResult("X", 10)).isEqualTo(new FrameResult("X", 10));
  }

  @Test
  void 프레임desc를_확인한다() {
    assertThat(frameResult.getFrameDesc()).isEqualTo("3|5");
  }

  @Test
  void 프레임Score를_확인한다() {
    assertThat(frameResult.getScore()).isEqualTo(8);
  }
}