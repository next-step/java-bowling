package bowling.domain.pin;

import bowling.domain.TestFixture;
import bowling.exception.InvalidPinCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("핀의 개수를 입력받아 핀을 생성한다.")
    void create(final int pinCount) {
        // given
        // when
        final Pin pin = new Pin(pinCount);

        // then
        assertThat(pin.pinCount()).isEqualTo(pinCount);
    }

    @Test
    @DisplayName("기본 생성자로 생성시 10개의 핀이 생성된다.")
    void defaultConstructor() {
        // given
        final int defaultPinCount = 10;

        // when
        final Pin pin = new Pin();

        // then
        assertThat(pin.pinCount()).isEqualTo(defaultPinCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("핀의 수는 0~10 이어야 한다.")
    void validate(final int invalidPinCount) {
        assertThatThrownBy(() -> new Pin(invalidPinCount))
                .isInstanceOf(InvalidPinCountException.class)
                .hasMessage(InvalidPinCountException.INVALID_PIN_COUNT);
    }

    @Test
    @DisplayName("Pin의 개수를 더할 수 있다.")
    void sum() {
        // given
        final int firstCount = 3;
        final int secondCount = 5;
        final Pin firstPin = new Pin(firstCount);
        final Pin secondPin = new Pin(secondCount);

        // when
        final Pin sum = firstPin.sum(secondPin);

        // then
        assertThat(sum.pinCount()).isEqualTo(firstCount + secondCount);
    }

    @Test
    @DisplayName("Pin이 최대치인지 확인한다.")
    void isMaximum() {
        // given
        final Pin maximumPin = TestFixture.STRIKE_PIN;
        final Pin nonMaximumPin = new Pin(3);

        // when
        // then
        assertAll(
                () -> assertThat(maximumPin.isMaximum()).isTrue(),
                () -> assertThat(nonMaximumPin.isMaximum()).isFalse()
        );
    }

    @Test
    @DisplayName("Pin이 Gutter인지 확인한다.")
    void isGutter() {
        // given
        final Pin gutterPin = TestFixture.GUTTER_PIN;
        final Pin nonGutterPin = new Pin(3);

        // when
        // then
        assertAll(
                () -> assertThat(gutterPin.isGutter()).isTrue(),
                () -> assertThat(nonGutterPin.isGutter()).isFalse()
        );
    }
}
