package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = { -1, 15 })
    @DisplayName("쓰러뜨린 핀 개수가 0~10 범위가 아닌 경우 예외")
    void testInvalidKnockedDownPins(int countOfPins) {
        Pins pins = new Pins();
        assertThatIllegalArgumentException().isThrownBy(() -> pins.knockedDown(countOfPins));
    }

    @Test
    @DisplayName("쓰러뜨릴 수 있는 핀 개수 범위를 초과하였을 경우")
    void testInvalidRangeDownPins() {
        // given
        Pins pins = new Pins();
        // when
        pins.knockedDown(5);
        // then
        assertThatIllegalArgumentException().isThrownBy(() -> pins.knockedDown(8));
    }

    @Test
    @DisplayName("쓰러뜨릴 수 있는 핀 범위 내로 두 번 정상 수행 (스페어)")
    void testKnockedDownPinsSpare() {
        // given
        Pins pins = new Pins();
        // when
        pins.knockedDown(5);
        pins.knockedDown(5);
        // then
        assertThat(pins.cannotRollMore()).isTrue();
        assertThat(pins.isAllPinsDown()).isTrue();
        assertThat(pins.isNotAvailable()).isTrue();
        assertThat(pins.getCountOfRemain()).isEqualTo(0);
    }

    @Test
    @DisplayName("쓰러뜨릴 수 있는 핀 범위 내로 두 번 정상 수행 (오픈)")
    void testKnockedDownPinsOpen() {
        // given
        Pins pins = new Pins();
        // when
        pins.knockedDown(5);
        pins.knockedDown(3);
        // then
        assertThat(pins.cannotRollMore()).isTrue();
        assertThat(pins.isAllPinsDown()).isFalse();
        assertThat(pins.isNotAvailable()).isTrue();
        assertThat(pins.getCountOfRemain()).isEqualTo(2);
    }

    @Test
    @DisplayName("쓰러뜨릴 수 있는 핀 범위 내로 두 번 정상 수행 (오픈)")
    void testKnockedDownPinsOneTime() {
        // given
        Pins pins = new Pins();
        // when
        pins.knockedDown(5);
        // then
        assertThat(pins.cannotRollMore()).isFalse();
        assertThat(pins.isAllPinsDown()).isFalse();
        assertThat(pins.isNotAvailable()).isFalse();
        assertThat(pins.getCountOfRemain()).isEqualTo(5);
    }

    @Test
    @DisplayName("핀 한번에 모두 쓰러뜨릴 경우 (스트라이크)")
    void testKnockedDownAllPins() {
        // given
        Pins pins = new Pins();
        // when
        pins.knockedDown(10);
        // then
        assertThat(pins.cannotRollMore()).isFalse(); // 횟수는 1번이라 다시 가능
        assertThat(pins.isAllPinsDown()).isTrue();
        assertThat(pins.isNotAvailable()).isTrue();
        assertThat(pins.getCountOfRemain()).isEqualTo(0);
    }

    @Test
    @DisplayName("핀 초기화")
    void testResetPins() {
        // given
        Pins pins = new Pins();
        // when
        pins.knockedDown(10);
        pins.reset();
        // then
        assertThat(pins.cannotRollMore()).isFalse();
        assertThat(pins.isAllPinsDown()).isFalse();
        assertThat(pins.isNotAvailable()).isFalse();
        assertThat(pins.getCountOfRemain()).isEqualTo(10);
    }
}
