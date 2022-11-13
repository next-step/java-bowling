package bowling;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {
    @Test
    @DisplayName("쓰러트린 핀의 수가 음수")
    void test1() {
        assertThatThrownBy(() -> {
            Pin.of(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("핀 더하기")
    void test3() {
        // given
        Pin pin1 = Pin.of(1);
        Pin pin2 = Pin.of(2);
        // when
        Pin pin = pin1.add(pin2);
        // then
        assertThat(pin).isEqualTo(Pin.of(3));
    }

    @Test
    @DisplayName("쓰러트린 핀이 없는가")
    void test4() {
        // given
        Pin pin = Pin.of(Pin.MIN_PIN_COUNT);
        // when
        // then
        assertThat(pin.areNoPinsDown()).isTrue();
    }

    @Test
    @DisplayName("모든 핀이 쓰러졌는가")
    void test5() {
        // given
        Pin pin = Pin.of(Pin.MAX_PIN_COUNT);
        // when
        // then
        assertThat(pin.areAllPinsDown()).isTrue();
    }

}
