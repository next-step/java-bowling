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
    @CsvSource(value = {"10:STRIKE", "8:NONE"}, delimiter = ':')
    void firstRoundStatus(int value, RoundsStatus expected) {
        assertThat(RoundsStatus.firstRoundStatus(value)).isEqualTo(expected);
    }

    @Test
    void firstRoundStatus_exception() {
        assertThatThrownBy(() -> RoundsStatus.firstRoundStatus(11))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_CLEAR_PIN_COUNT.getErrorMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"10:SPARE", "8:MISS"}, delimiter = ':')
    void getRoundStatus(int value, RoundsStatus expected) {
        assertThat(RoundsStatus.getRoundStatus(value)).isEqualTo(expected);
    }

    @Test
    void getRoundStatus_exception() {
        assertThatThrownBy(() -> RoundsStatus.getRoundStatus(11))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_CLEAR_PIN_COUNT.getErrorMessage());
    }
}
