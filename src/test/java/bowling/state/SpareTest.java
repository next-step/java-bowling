package bowling.state;

import static org.assertj.core.api.Assertions.assertThat;


import bowling.pin.PinTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {

  @DisplayName("스페어 판별이 되엇을때 스페어 마크를 출력하는지 확인한다.")
  @Test
  void spareMark() {
    Spare spare = new Spare(PinTest.from(5), PinTest.from( 5));
    assertThat(spare.score()).isEqualTo("5|/");
  }

  @DisplayName("스페어 판별이 되엇을때 쓰러트린 핀 개수를 출력하는지 확인한다.")
  @Test
  void spareTotalPin() {
    Spare spare = new Spare(PinTest.from(5), PinTest.from( 5));
    assertThat(spare.totalPin()).isEqualTo(10);
  }
}