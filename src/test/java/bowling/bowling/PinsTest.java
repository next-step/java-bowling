package bowling.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PinsTest {

    @DisplayName("첫 번째 투구에서 모든 핀을 쓰러트리면 스트라이크")
    @Test
    void strike() {
        Pins pins = new Pins(10);

        assertAll(
                () -> assertThat(pins.isStrike()).isTrue(),
                () -> assertThat(pins.isSpare()).isFalse(),
                () -> assertThat(pins.isMiss()).isFalse(),
                () -> assertThat(pins.isGutter()).isFalse()
        );
    }

    @DisplayName("두 번째 투구에서 모든 핀을 쓰러트리면 스페어")
    @Test
    void spare() {
        Pins pins = new Pins(3);
        pins.bowl(7);

        assertAll(
                () -> assertThat(pins.isSpare()).isTrue(),
                () -> assertThat(pins.isStrike()).isFalse(),
                () -> assertThat(pins.isMiss()).isFalse(),
                () -> assertThat(pins.isGutter()).isFalse()
        );
    }

    @DisplayName("프레임의 두 번째 투구에서도 모든 핀이 쓰러지지 않으면 미스")
    @Test
    void miss() {
        Pins pins = new Pins(3);
        pins.bowl(3);

        assertAll(
                () -> assertThat(pins.isMiss()).isTrue(),
                () -> assertThat(pins.isSpare()).isFalse(),
                () -> assertThat(pins.isStrike()).isFalse(),
                () -> assertThat(pins.isGutter()).isFalse()
        );
    }

    @DisplayName("핀을 하나도 쓰러트리지 못한 상태면 거터")
    @Test
    void gutter() {
        Pins pins = new Pins(0);
        pins.bowl(0);

        assertAll(
                () -> assertThat(pins.isGutter()).isTrue(),
                () -> assertThat(pins.isMiss()).isFalse(),
                () -> assertThat(pins.isSpare()).isFalse(),
                () -> assertThat(pins.isStrike()).isFalse()
        );
    }
}