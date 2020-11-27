package bowling.domain;

import bowling.exception.RollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RollTest {
    @ParameterizedTest
    @DisplayName("count 가 0부터 10의 숫자가 아니면, BadCountOfPinsException 이 발생한다.")
    @ValueSource(ints = {-1, 11})
    void constructor(int count) {
        assertThatExceptionOfType(RollException.class)
                .isThrownBy(() -> Roll.of(count));
    }
}
