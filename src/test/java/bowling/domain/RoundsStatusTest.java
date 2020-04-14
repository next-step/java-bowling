package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RoundsStatusTest {
    @ParameterizedTest
    @CsvSource(value = {"0:1:NONE", "0:10:STRIKE", "1:10:SPARE", "1:8:MISS"}, delimiter = ':')
    void getRoundStatus(int index, int value, RoundsStatus expected) {
        assertThat(RoundsStatus.getRoundStatus(index, value)).isEqualTo(expected);
    }

    @Test
    void getRoundStatus_exception() {
        assertThatThrownBy(() -> RoundsStatus.getRoundStatus(0, 11))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_CLEAR_PIN_COUNT.getErrorMessage());
    }
}
