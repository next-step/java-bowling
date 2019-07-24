package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FirstRollTest {

  FirstRoll firstRoll;

  @BeforeEach
  void 생성() {
    firstRoll = new FirstRoll(5);
  }

  @Test
  void 첫_투구는_프레임_끝이아니다() {
    assertThat(firstRoll.isFinish()).isFalse();
  }

  @Test
  void 다음투구와_합이_10이면_Spare를_리턴한다() {
    assertThat(firstRoll.roll(5)).isInstanceOf(Spare.class);
  }

  @Test
  void 다음투구와_합이_10이_안되면_Miss를_리턴한다() {
    assertThat(firstRoll.roll(4)).isInstanceOf(Miss.class);
  }

  @Test
  void 두번넘어진_핀의합이_10이_넘어갈수_없다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      firstRoll.roll(6);
    });
  }

}