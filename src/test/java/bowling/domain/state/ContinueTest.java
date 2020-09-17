package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContinueTest {

    @Test
    @DisplayName("Continue 상태에서 이전 핀을 포함하여 1~9개의 핀을 쓰러뜨린 경우 MISS 상태가 되는 테스트")
    void continue_to_miss_test() {
        assertThat(Continue.of(Pin.of(3))
                .bowl(Pin.of(0)))
                .isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("Continue 상태에서 모든 핀을 쓰러뜨리면 Spare 상태가 되는 테스트")
    void continue_to_spare_test() {
        assertThat(Continue.of(Pin.of(7))
                .bowl(Pin.of(3)))
                .isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("Continue 상태에서 이전 핀을 포함하여 아무 핀도 쓰러뜨리지 못한 경우 Gutter 상태가 되는 테스트")
    void continue_to_gutter_test() {
        assertThat(Continue.of(Pin.of(0))
                .bowl(Pin.of(0)))
                .isInstanceOf(Gutter.class);
    }

}
