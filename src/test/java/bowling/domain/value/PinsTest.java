package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("볼링핀 테스트")
class PinsTest {

    @DisplayName("정상적으로 핀 생성")
    @RepeatedTest(10)
    void create(RepetitionInfo repetitionInfo) {
        int number = repetitionInfo.getCurrentRepetition();
        assertDoesNotThrow(
                () -> Pins.from(number));
    }

    @Test
    @DisplayName("잘못된 핀 예외 검증")
    void create_exception() {
        assertAll(() -> {
            assertThatIllegalArgumentException().isThrownBy(() -> Pins.from(-1));
            assertThatIllegalArgumentException().isThrownBy(() -> Pins.from(11));
        });
    }

    @Test
    @DisplayName("핀을 하나도 쓰러트리지 못한 경우 거터로 처리 검증")
    void is_gutter() {
        Pins pins = Pins.from(0);
        assertThat(pins.isGutter()).isTrue();
    }

    @Test
    @DisplayName("핀을 전부 쓰러트린 경우 스트라이크 처리 검증")
    void is_strike() {
        Pins pins = Pins.from(10);
        assertThat(pins.isStrike()).isTrue();
    }


    @Test
    @DisplayName("몇개의 핀을 쓰러트린 경우 거터와 스트라이크 처리 검증")
    void is_normal() {
        Pins pins = Pins.from(5);
        assertAll(() -> {
            assertThat(pins.isGutter()).isFalse();
            assertThat(pins.isStrike()).isFalse();
        });
    }
}

