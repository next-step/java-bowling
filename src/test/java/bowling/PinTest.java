package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {
    public static final Pin pin = Pin.of();

    @Test
    void 핀을생성() {
        assertThat(pin).isEqualTo(Pin.of(0, 10));
    }

    @Test
    void 시도횟수는_0과2사이() {
        assertThatThrownBy(() -> {
            Pin.of(4, 10);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 핀의수는_0과10사이() {
        assertThatThrownBy(() -> {
            Pin.of(3, 11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 핀이넘어지다() {
        assertThat(pin.fallen(RollNumber.of(1))).isEqualTo(Pin.of(1, 9));
    }

    @Test
    void 핀에따른_투구결과를확인() {

    }
}
