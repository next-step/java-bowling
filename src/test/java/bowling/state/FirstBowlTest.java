package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import bowling.Pins;
import org.junit.jupiter.api.Test;

class FirstBowlTest {

  @Test
  void 생성() {
    FirstBowl firstBowl = new FirstBowl(new Pins(5));
  }

  @Test
  void 동치테스트() {
    assertThat(new FirstBowl(new Pins(5))).isEqualTo(new FirstBowl(new Pins(5)));
  }

  @Test
  void 한프레임의_두개의_투구의_합은_10보다_클수없다() {
    assertThatIllegalArgumentException().isThrownBy(()->{
      new FirstBowl(new Pins(5)).bowl(new Pins(6));
    });
  }

  @Test
  void FirstBowl은_프레임의_끝이아니다() {
    assertThat(new FirstBowl(new Pins(5)).isFinish()).isFalse();
  }

  @Test
  void 두번째투구로_스페어가되면_Spare를_리턴한다() {
    FirstBowl firstBowl = new FirstBowl(new Pins(3));

    assertThat(firstBowl.bowl(new Pins(7))).isInstanceOf(Spare.class);
  }

  @Test
  void 두번째투구로_미스가_되면_Miss를_리턴한다() {
    FirstBowl firstBowl = new FirstBowl(new Pins(5));

    assertThat(firstBowl.bowl(new Pins(3))).isInstanceOf(Miss.class);
  }

  @Test
  void FirstBowl_desc_확인() {
    assertThat(new FirstBowl(new Pins(5)).desc()).isEqualTo("5");
  }
}