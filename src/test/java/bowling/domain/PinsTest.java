package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PinsTest {

  @Test
  @DisplayName("[Pins] 전체 쓰러트린 핀 카운트 테스트")
  void total_hit_pin_test() {
    List<Pin> pin = Stream.of(5, 5)
        .map(Pin::new)
        .collect(Collectors.toList());
    Pins pins = new Pins(pin);

    assertThat(pins.totalHitPin()).isEqualTo(10);
  }

  @Test
  @DisplayName("[Pins] 두번째 투구까지 던진 핀 카운트 테스트")
  void count_upto_second_test() {
    List<Pin> pin = Stream.of(4, 6, 10)
        .map(Pin::new)
        .collect(Collectors.toList());
    Pins pins = new Pins(pin);

    assertThat(pins.countUptoSecondThrow()).isEqualTo(10);
  }
}
