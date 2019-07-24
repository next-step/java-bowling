package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FallDownPinsTest {

  FallDownPins fallDownPins;

  @BeforeEach
  void 생성() {
    fallDownPins = new FallDownPins();
  }

  @Test
  void 동치비교() {
    assertThat(fallDownPins.roll(5)).isEqualTo(fallDownPins.roll(5));
  }

  @Test
  void 핀은_한번에_10개이상_넘어가지_못한다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      fallDownPins.roll(11);
    });
  }

  @Test
  void 핀이_넘어간수는_양수다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      fallDownPins.roll(-1);
    });
  }

  @Test
  void 핀이_처음에10개_넘어가면_프레임이끝난다() {
    assertThat(fallDownPins.roll(10).isFinish()).isEqualTo(true);
  }

  @Test
  void 핀이_두번쓰러지면_프레임이끝난다() {
    assertThat(fallDownPins.roll(5).roll(5).isFinish()).isEqualTo(true);
  }

  @Test
  void 두번넘어진_핀의합이_10이_넘어갈수_없다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      fallDownPins.roll(5).roll(6);
    });
  }

}
