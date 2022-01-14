package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @Test
    void 쓰러트린_개수_심볼() {
        Throwing spare = new Spare(new Pins(4), new Pins(6));
        assertThat(spare.symbol()).isEqualTo("4|/");
    }

    @Test
    void 예외_스페어는_쓰러트린_핀이_10개() {
        assertThatThrownBy(() -> new Spare(new Pins(4), new Pins(3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("스페어는 %d개를 쓰러트려야 합니다.", Pins.MAX_PINS_COUNT));
    }
}