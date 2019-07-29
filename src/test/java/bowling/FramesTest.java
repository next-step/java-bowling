package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FramesTest {

  Frames frames;

  @BeforeEach
  void 생성() {
    frames = new Frames();
  }

  @Test
  void Strike를_하면_다음프레임이_리턴된다() {
    assertThat(frames.roll(10).getFrameNo()).isEqualTo(2);
  }

  @Test
  void Spare를하면_다음프레임이_리턴된다() {
    frames.roll(8);
    Frame nextFrame = frames.roll(2);
    assertThat(nextFrame.getFrameNo()).isEqualTo(2);
  }

  @Test
  void Miss를하면_다음프레임이_리턴된다() {
    frames.roll(8);
    Frame nextFrame = frames.roll(1);
    assertThat(nextFrame.getFrameNo()).isEqualTo(2);
  }

  @Test
  void 스트라이크가아닌_처음투구후에는_현재프레임이_리턴된다() {
    assertThat(frames.roll(8).getFrameNo()).isEqualTo(1);
  }

}
