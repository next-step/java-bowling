package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertSame;

import bowling.exception.score.PinValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {30, 11, 20})
    @DisplayName("핀은 0~10번의 값이 들어오지 않으면 Exception이 발생해야 한다.")
    void pinSaveFailTest(int input) {

        // when & then
        assertThatExceptionOfType(PinValueException.class)
            .isThrownBy(() -> Pin.of(input))
            .withMessageMatching("핀은 0~10 사이의 값만 저장할 수 있다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    @DisplayName("static 캐싱 테스트")
    void staticCacheTest(int input) {

        // when
        Pin pin = Pin.of(input);

        // then
        assertSame(pin, Pin.of(input));
    }

    @Test
    @DisplayName("equals, hashcode 테스트")
    void equalsHashCodeTest() {

        // given
        Pin pin = Pin.of(1);
        Pin copyPin = Pin.of(1);

        // when & then
        assertThat(pin)
            .isEqualTo(copyPin)
            .hasSameHashCodeAs(copyPin);
    }

}
