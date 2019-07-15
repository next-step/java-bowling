package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

public class FallDownPinsTest {

  @Test
  void 생성() {
    FallDownPins fallDownPins = FallDownPins.first(5);
  }

  @Test
  void 동치비교() {
    assertThat(FallDownPins.first(5)).isEqualTo(FallDownPins.first(5));
  }

  @Test
  void 핀은_한번에_10개이상_넘어가지_못한다() {
    assertThatIllegalArgumentException().isThrownBy(() ->{
      FallDownPins.first(11);
    });
  }

  @Test
  void 핀이_넘어간수는_양수다() {
    assertThatIllegalArgumentException().isThrownBy(() ->{
      FallDownPins.first(-1);
    });
  }

  @Test
  void 핀이_처음에10개_넘어가면_프레임이끝난다() {
    assertThat(FallDownPins.first(10).isFinish()).isEqualTo(true);
  }

  @Test
  void 핀이_두번쓰러지면_프레임이끝난다() {
    assertThat(FallDownPins.first(5).second(5).isFinish()).isEqualTo(true);
  }
}
