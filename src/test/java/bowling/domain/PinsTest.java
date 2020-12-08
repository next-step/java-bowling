package bowling.domain;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.domain.Pins.INVALID_PIN_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PinsTest {
    @Test
    void create_success() {
        // given
        final int fallenPin = 3;

        // when
        final Pins pins = Pins.of(fallenPin);

        // then
        assertThat(pins).isNotNull();
    }

    @DisplayName("핀의 개수가 유효 하지 못한 경우 예외 throw")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void create_throw_exception_when_number_of_fallen_pin_invalid(final int invalidFallenPin) {
        // when 
        final Throwable thrown = catchThrowable(() -> Pins.of(invalidFallenPin));

        // then
        AssertionsForClassTypes.assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PIN_COUNT);
    }
}