package bowling.bowlingscore.domain.pitching;

import bowling.bowlingscore.exception.CustomException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @ParameterizedTest(name = "Pins 생성 [{index}] {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}) // given
    void create(int pin) {
        // when
        Pins pins = new Pins(pin);
        // then
        assertThat(pins).isEqualTo(new Pins(pin));
    }

    @ParameterizedTest(name = "Pins 생성 실패 : 허용 범위 초과 [{index}] {0}")
    @ValueSource(ints = {-1, 11}) // given
    void create_fail(int pins) {
        // when, then
        assertThatThrownBy(() -> new Pins(pins))
                .isInstanceOf(CustomException.class);
    }
}