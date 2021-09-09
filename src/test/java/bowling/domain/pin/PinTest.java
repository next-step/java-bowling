package bowling.domain.pin;

import bowling.exception.PinOutOfBoundsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Pin Test")
public class PinTest {

    @DisplayName("생성")
    @ParameterizedTest
    @ValueSource(ints = {Pin.MIN, Pin.MIN + 1, Pin.MAX - 1, Pin.MAX})
    void generatePin(int knockDownNumber) {
        //given
        //when
        Pin pin = Pin.valueOf(knockDownNumber);

        //then
        assertThat(pin).isEqualTo(Pin.valueOf(knockDownNumber));
    }

    @Test
    @DisplayName("Minimum 확인")
    void minimumPin() {
        //given
        Pin minimumPin = Pin.valueOf(Pin.MIN);
        Pin notMinimumPin = Pin.valueOf(Pin.MIN + 1);

        // when
        // then
        assertAll(
                () -> assertThat(minimumPin.isMinimum()).isTrue(),
                () -> assertThat(notMinimumPin.isMinimum()).isFalse()
        );
    }

    @Test
    @DisplayName("Maximum 확인")
    void maximumPin() {
        //given
        Pin maximumPin = Pin.valueOf(Pin.MAX);
        Pin notMaximumPin = Pin.valueOf(Pin.MAX - 1);

        // when
        // then
        assertAll(
                () -> assertThat(maximumPin.isMaximum()).isTrue(),
                () -> assertThat(notMaximumPin.isMaximum()).isFalse()
        );
    }

    @DisplayName("Pin 숫자가 범위에 벗어날 때 Exception")
    @ParameterizedTest
    @ValueSource(ints = {Pin.MIN - 1, Pin.MAX + 1})
    void pinOutOfBoundsException(int knockDownNumber) {
        assertThatThrownBy(() -> Pin.valueOf(knockDownNumber))
                .isInstanceOf(PinOutOfBoundsException.class);
    }
}
