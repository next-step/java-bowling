package bowling.domain;

import bowling.bowlingexception.InvalidDownedPinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DownedPinTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("0 ~ 10 까지의 범위 이외에 대한 예외처리")
    void testRangeValidation(int input) {
        assertThatThrownBy(
                () -> DownedPin.from(input)
        ).isInstanceOf(InvalidDownedPinNumberException.class);
    }
}
