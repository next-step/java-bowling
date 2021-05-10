package bowling.domain.result;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultTest {
  @Test
  @DisplayName("스코어 확정일 때 출력 여부 테스트")
  void normalTest() {
    FrameResult frameResult = new FrameResult("8|1", 9, true);

    assertThat(frameResult.score()).isEqualTo("9");
  }

  @Test
  @DisplayName("스코어 비확정일 때 출력 여부 테스트")
  void test() {
    FrameResult frameResult = new FrameResult("X", 10, false);

    assertThat(frameResult.score()).isEqualTo("");
  }
}