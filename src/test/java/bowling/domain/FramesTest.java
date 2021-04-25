package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FramesTest {

  @Test
  @DisplayName("[Frames] Frames init 테스트")
  void init_frames_test() {
    Frames frames = Frames.init();
    int frameSize = frames.frames().size();
    assertThat(frameSize).isEqualTo(10);
  }
}
