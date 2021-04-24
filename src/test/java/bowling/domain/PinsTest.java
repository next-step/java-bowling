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

    assertThat(pins.countUptoSecondPin()).isEqualTo(10);
  }

  @Test
  @DisplayName("[Pins] 스트라이크인 경우 최종 state는 한개")
  void strike_state_test() {
    List<Pin> pin = Stream.of(10)
        .map(Pin::new)
        .collect(Collectors.toList());
    Pins pins = new Pins(pin);

    int size = pins.frameState().size();

    assertThat(size).isEqualTo(1);
  }

  @Test
  @DisplayName("[Pins] 스페어인 경우 최종 state는 두개")
  void spare_state_test() {
    List<Pin> pin = Stream.of(5, 5)
        .map(Pin::new)
        .collect(Collectors.toList());
    Pins pins = new Pins(pin);

    int size = pins.frameState().size();

    assertThat(size).isEqualTo(2);
  }

  @Test
  @DisplayName("[Pins] 보너스 프레임 최종 state는 세개")
  void bonuse_frame_state_test() {
    List<Pin> pin = Stream.of(4, 6, 10)
        .map(Pin::new)
        .collect(Collectors.toList());
    Pins pins = new Pins(pin);

    int size = pins.frameState().size();

    assertThat(size).isEqualTo(3);
  }
}
