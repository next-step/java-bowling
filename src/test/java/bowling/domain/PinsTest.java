package bowling.domain;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("핀을 생성시에는 미스 상태이다")
    @Test
    void pinsCreateTest() {
        // Given
        Pins pins = Pins.create();
        // When && Then
        assertThat(pins.status()).isEqualTo(Status.MISS);
    }

    @DisplayName("핀을 1구에 다 쓰러뜨리면 스트라이크이다")
    @Test
    void whenDownAllPinsThenReturnStrike() {
        // Given
        Pins pins = Pins.create();
        Pins roll = pins.roll(10);
        assertThat(roll.status()).isEqualTo(Status.STRIKE);
    }

    @DisplayName("핀을 2구에 다 쓰러뜨리면 스페어다")
    @Test
    void ThenReturnSpare() {
        // Given
        Pins pins = Pins.create();
        Pins pins2 = pins.roll(5);
        Pins pins3 = pins2.roll(5);

        assertThat(pins3.status()).isEqualTo(Status.SPARE);
    }

    @DisplayName("핀을 하나도 못 쓰러뜨리면 거터이다.")
    @Test
    void ThenReturnGutter() {
        // Given
        Pins pins = Pins.create();
        Pins pins2 = pins.roll(5);
        Pins pins3 = pins2.roll(0);

        assertThat(pins3.status()).isEqualTo(Status.GUTTER);
    }

    @DisplayName("2구 동안 핀을 다 못쓰러뜨리면 miss.")
    @Test
    void ThenReturnMiss() {
        // Given
        Pins pins = Pins.create();
        Pins pins2 = pins.roll(5);
        Pins pins3 = pins2.roll(4);

        assertThat(pins3.status()).isEqualTo(Status.MISS);
    }
}
