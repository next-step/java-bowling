package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NormalFrameTest {

  @Test
  void 생성() {
    Frame normalFrame = NormalFrame.first();
  }

  @Test
  void 첫번째_시도에_10개를_쓰러뜨린후__다음프레임을_호출하면_다음프레임이_리턴된다() {
    Frame firstFrame = NormalFrame.first();
    assertThat(firstFrame.roll(10).nextFrame().getFrameNo()).isEqualTo(2);
  }

  @Test
  void 두번_roll을하면_다음_프레임이_리턴된다() {
    Frame firstFrame = NormalFrame.first();
    assertThat(firstFrame.roll(5).roll(5).nextFrame().getFrameNo()).isEqualTo(2);
  }

  @Test
  void Miss일때_점수를_가지고온다() {
    Frame firstFrame = NormalFrame.first();
    assertThat(firstFrame.roll(3).roll(5).score()).isEqualTo(8);
  }

  @Test
  void Strike일때_점수를_가지고온다() {
    Frame firstFrame = NormalFrame.first();
    firstFrame.roll(10);

    Frame nextFrame = firstFrame.nextFrame();
    nextFrame.roll(5);
    nextFrame.roll(3);

    assertThat(firstFrame.score()).isEqualTo(18);
  }

  @Test
  void Strike일때_점수가_다_더해지지_않았을때는_마이너스1을_가지고온다() {
    Frame firstFrame = NormalFrame.first();
    firstFrame.roll(10);

    Frame nextFrame = firstFrame.nextFrame();
    nextFrame.roll(5);

    assertThat(firstFrame.score()).isEqualTo(-1);
  }

  @Test
  void Spare일때_점수를_가지고온다() {
    Frame firstFrame = NormalFrame.first();
    firstFrame.roll(5);
    firstFrame.roll(5);

    Frame nextFrame = firstFrame.nextFrame();
    nextFrame.roll(6);
    nextFrame.roll(2);

    assertThat(firstFrame.score()).isEqualTo(16);
  }

}
