package bowling;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

public class FallDownPinTest {

  @Test
  void 생성() {
    FallDownPin fallDownPins = FallDownPin.of(1);
  }

  @Test
  void 핀은_한번에_10개이상_넘어가지_못한다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      FallDownPin.of(11);
    });
  }

  @Test
  void 핀이_넘어간수는_양수다() {
    assertThatIllegalArgumentException().isThrownBy(() -> {
      FallDownPin.of(-1);
    });
  }

  @Test
  void 핀이_10개넘어가면_strike다() {
    assertThat(FallDownPin.of(10).isStrike()).isTrue();
  }

  @Test
  void 두번에_10개를_다_넘기면_spare다() {
    assertThat(FallDownPin.of(8).isSpare(FallDownPin.of(2))).isTrue();
  }
}
