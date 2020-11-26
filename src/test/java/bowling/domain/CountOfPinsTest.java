package bowling.domain;

import bowling.exception.BadCountOfPinsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CountOfPinsTest {
    @ParameterizedTest
    @DisplayName("count 가 0부터 10의 숫자가 아니면, BadCountOfPinsException 이 발생한다.")
    @ValueSource(ints = {-1, 11})
    void constructor(int count) {
        assertThatExceptionOfType(BadCountOfPinsException.class)
                .isThrownBy(() -> CountOfPins.of(count));
    }
}
