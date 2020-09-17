package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @Test
    @DisplayName("Spare 상태에서는 더 이상 공을 굴릴 수 없는 테스트")
    void spare_throw_exception_test() {
        assertThatThrownBy(() -> Strike.of().bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Continue 상태에서 나머지 핀을 모두 쓰러뜨린 경우 Spare 상태를 가지는 테스트")
    void continue_to_spare_test() {
        assertThat(Continue.of(Pin.of(7)).bowl(Pin.of(3)))
                .isEqualTo(Spare.of(Pin.of(7)));
    }
}
