package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NormalFrameTest {

  @Test
  void 생성() {
    Frame normalFrame = NormalFrame.first();
  }

  @Test
  void 동치테스트() {
    assertThat(NormalFrame.first()).isEqualTo(NormalFrame.first());
  }

  @Test
  void 첫번째_시도에_10개를_쓰러뜨린후__다음프레임을_호출하면_다음프레임이_리턴된다() {
    Frame firstFrame = NormalFrame.first();
    assertThat(firstFrame.roll(10).nextFrame()).isEqualTo(new NormalFrame(2));
  }

  @Test
  void 두번_roll을하면_다음_프레임이_리턴된다() {
    Frame firstFrame = NormalFrame.first();
    assertThat(firstFrame.roll(5).roll(5).nextFrame()).isEqualTo(new NormalFrame(2));
  }

  @Test
  void toStringTest() {
    Frame firstFrame = NormalFrame.first();
    System.out.println(firstFrame.roll(5).roll(4).toString());
  }
}
