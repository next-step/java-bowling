package bowling.domain;

import bowling.exception.InvalidPinsSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @DisplayName("Pins 인스턴스 생성 테스트")
    @Test
    void 생성() {
        // given
        int hitCount = 10;

        // when
        Pins pins = Pins.valueOf(hitCount);

        // then
        assertThat(pins).isNotNull();
    }

    @DisplayName("Pins 인스턴스에 범위를 벗어난 값 주입시 예외처리 테스트")
    @Test
    void 검증() {
        // given
        int negativeCount = -1;
        int overCount = 11;

        // when and then
        assertThatThrownBy(() -> Pins.valueOf(negativeCount))
                .isInstanceOf(InvalidPinsSizeException.class)
                .hasMessage("Pins 의 범위를 벗어난 값이 입력되었습니다.");

        // when and then
        assertThatThrownBy(() -> Pins.valueOf(overCount))
                .isInstanceOf(InvalidPinsSizeException.class)
                .hasMessage("Pins 의 범위를 벗어난 값이 입력되었습니다.");
    }
}