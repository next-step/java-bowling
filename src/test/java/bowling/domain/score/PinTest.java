package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 15, 100})
    @DisplayName("pin은 0~10 사이의 값만 저장할 수 있다.")
    void pinSaveRangeExceptionTest(int input) {

        // when & then
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> Pin.of(input))
            .withMessageMatching("Pin은 0 ~ 10 사이의 수만 저장될 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 10})
    @DisplayName("Pin 캐싱 테스트")
    void pinCacheTest(int input) {

        // when
        Pin pin = Pin.of(input);

        // then
        assertTrue(pin == Pin.of(input));
        assertThat(pin).isEqualTo(Pin.of(input));
    }

    @Nested
    @DisplayName("스트라이크인지 확인할 수 있다.")
    class isStrikeTest {

        @Test
        @DisplayName("true")
        void trueTest() {

            // given
            Pin pin = Pin.of(10);

            // when
            boolean result = pin.isStrike();

            // then
            assertTrue(result);
        }

        @Test
        @DisplayName("false")
        void falseTest() {

            // given
            Pin pin = Pin.of(7);

            // when
            boolean result = pin.isStrike();

            // then
            assertFalse(result);
        }
    }

    @Test
    @DisplayName("Pin equals, hashCode 재정의 테스트")
    void pinEqualsHashCodeTest() {

        // given & when
        Pin result = Pin.of(5);

        // then
        assertThat(result)
            .isEqualTo(Pin.of(5))
            .hasSameHashCodeAs(Pin.of(5));
    }

}