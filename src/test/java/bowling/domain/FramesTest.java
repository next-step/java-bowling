package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest {

  @Test
  @DisplayName("[Frames] Frames init 테스트")
  void init_frames_test() {
    Frames frames = Frames.init();

    int frameSize = frames.frames().size();

    assertThat(frameSize).isEqualTo(1);
  }

  @Test
  @DisplayName("[Frames] 공을 던진 후 다음 프레임이 만들어지는지 테스트")
  void add_frame_test() {
    Frames frames = Frames.init();
    frames.throwBall(10);

    int frameSize = frames.frames().size();

    assertThat(frameSize).isEqualTo(2);
  }
}
