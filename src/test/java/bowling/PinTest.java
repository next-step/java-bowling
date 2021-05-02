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
    void strike핀의_결과확인() {
        Pin after = pin.fallen(RollNumber.of(10));
        assertThat(after.checkResult()).isEqualTo(RollResult.of());
    }

    @Test
    void spare핀의_결과확인() {
        Pin after = pin.fallen(RollNumber.of(7));
        after = after.fallen(RollNumber.of(3));
        assertThat(after.checkResult()).isEqualTo(RollResult.of());
    }

    @Test
    void miss핀의_결과확인() {
        Pin after = pin.fallen(RollNumber.of(0));
        after = after.fallen(RollNumber.of(0));
        assertThat(after.checkResult()).isEqualTo(RollResult.of());
    }

    @Test
    void gutter핀의_결과확인() {
        Pin after = pin.fallen(RollNumber.of(7));
        after = after.fallen(RollNumber.of(1));
        assertThat(after.checkResult()).isEqualTo(RollResult.of());
    }
}
