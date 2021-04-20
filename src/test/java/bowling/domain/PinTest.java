package bowling.domain;

import bowling.exception.PinOutOfSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PinTest {

    @DisplayName("핀 비교 테스트")
    @Test
    void testCase1() {
        // given
        Pin firstPin = Pin.of(3);
        // when
        Pin expected = Pin.of(3);
        // then
        assertThat(firstPin).isEqualTo(expected);
    }

    @DisplayName("핀 범위 예외 처리 테스트")
    @ParameterizedTest(name = "{0} 값 입력 시 핀 예외 처리 테스트")
    @ValueSource(ints = {-1, 11})
    void testCase2(int given) {
        assertThatExceptionOfType(PinOutOfSizeException.class)
                .isThrownBy(() -> Pin.of(given));
    }

}
