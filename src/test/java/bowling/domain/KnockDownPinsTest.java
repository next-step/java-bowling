package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class KnockDownPinsTest {
    @Test
    public void createTest() {
        assertThatCode(() -> KnockDownPins.valueOf(10))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("쓰러트린 핀의 갯수는 0개 ~ 10개로 제한한다.")
    public void invalidLengthTest(int invalidKnockDownPins) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> KnockDownPins.valueOf(invalidKnockDownPins))
                .withMessage(KnockDownPins.INVALID_KNOCK_DOWN_PINS_VALUE);
    }
}
