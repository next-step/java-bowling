package bowling.domain.pins;

import bowling.domain.frame.info.NormalFrameInfo;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {

    @DisplayName("핀을 생성시에는 준비 상태이다")
    @Test
    void pinsCreateTest() {
        // Given
        Pins pins = Pins.create();
        // When && Then
        assertThat(pins.status()).isEqualTo(Status.READY);
    }

    @DisplayName("핀을 1구에 다 쓰러뜨리면 스트라이크이다")
    @Test
    void whenDownAllPinsThenReturnStrike() {
        // Given
        Pins pins = Pins.create();
        Pins roll = pins.roll(Score.from(10), NormalFrameInfo.of(0, 0));
        assertThat(roll.status()).isEqualTo(Status.STRIKE);
    }

    @DisplayName("핀을 2구에 다 쓰러뜨리면 스페어다")
    @Test
    void ThenReturnSpare() {
        // Given
        Pins pins = Pins.create();
        Pins pins2 = pins.roll(Score.from(5), NormalFrameInfo.of(0, 0));
        Pins pins3 = pins2.roll(Score.from(5), NormalFrameInfo.of(0, 1));

        assertThat(pins3.status()).isEqualTo(Status.SPARE);
    }

    @DisplayName("핀을 하나도 못 쓰러뜨리면 거터이다.")
    @Test
    void ThenReturnGutter() {
        // Given
        Pins pins = Pins.create();
        Pins pins2 = pins.roll(Score.from(5), NormalFrameInfo.of(0, 0));
        Pins pins3 = pins2.roll(Score.create(), NormalFrameInfo.of(0, 1));

        assertThat(pins3.status()).isEqualTo(Status.GUTTER);
    }

    @DisplayName("2구 동안 핀을 다 못쓰러뜨리면 miss.")
    @Test
    void ThenReturnMiss() {
        // Given
        Pins pins = Pins.create();
        Pins pins2 = pins.roll(Score.from(5), NormalFrameInfo.of(0, 0));
        Pins pins3 = pins2.roll(Score.from(4), NormalFrameInfo.of(0, 1));

        assertThat(pins3.status()).isEqualTo(Status.MISS);
    }
}
