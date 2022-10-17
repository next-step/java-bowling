package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class PinsTest {

    @Test
    @DisplayName("두 번 굴리고 나면 턴 종료")
    void is_finished_when_bowl_twice() {
        //given
        Pins pins = Pins.of(new Pin(5));
        //when
        pins.bowl(new Pin(5));
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> pins.bowl(new Pin(3)));
    }

    @Test
    @DisplayName("스트라이크면 턴 종료")
    void is_finished_when_strike() {
        //given
        Pins pins = Pins.of(new Pin(10));
        //then
        assertThatIllegalStateException()
                .isThrownBy(() -> pins.bowl(new Pin(3)));
    }

    @Test
    @DisplayName("스코어 합이 10 초과면 IllegalArgumentException")
    void sum_over_10() {
        //given
        Pins pins = Pins.of(new Pin(6));
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> pins.bowl(new Pin(6)));
    }

    @Test
    @DisplayName("스페어")
    void spare() {
        //given
        Pins pins = Pins.of(new Pin(7));
        //when
        pins.bowl(new Pin(3));
        //then
        assertAll(
                () -> assertThat(pins.isSpare()).isTrue(),
                () -> assertThat(pins.isMiss()).isFalse()
        );
    }

    @Test
    @DisplayName("미스")
    void miss() {
        //given
        Pins pins = Pins.of(new Pin(7));
        //when
        pins.bowl(new Pin(1));
        //then
        assertAll(
                () -> assertThat(pins.isMiss()).isTrue(),
                () -> assertThat(pins.isSpare()).isFalse()
        );
    }

    @Test
    @DisplayName("합산")
    void sum() {
        //given
        Pins pins = Pins.of(new Pin(1));
        //then
        assertThat(pins.getRemainPins()).isEqualTo(9);
    }

}